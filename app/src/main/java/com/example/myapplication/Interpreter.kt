package com.example.myapplication

class Interpreter(private var parser: Parser) {

    var globalVar = mutableMapOf<String?, Int>()

    var outputStr: String = ""
    private fun visitBinOp(node: BinOp): Int {
        return when (node.op.type) {
            TypeToken.PLUS.typeName -> {
                this.visit(node.left)!! + this.visit(node.right)!!
            }
            TypeToken.MINUS.typeName -> {
                this.visit(node.left)!! - this.visit(node.right)!!
            }
            TypeToken.MUL.typeName -> {
                this.visit(node.left)!! * this.visit(node.right)!!
            }
            TypeToken.DIV.typeName -> {
                this.visit(node.left)!! / this.visit(node.right)!!
            }
            else -> -1
        }
    }

    private fun visitNum(node: Num): Int {
        return node.value
    }

    private fun visitUnaryOp(node: UnaryOp): Int {
        val op = node.op.type
        if (op == TypeToken.PLUS.typeName) {
            return +this.visit(node.expr)!!
        } else if (op == TypeToken.MINUS.typeName) {
            return -this.visit(node.expr)!!
        }
        return -1
    }

    private fun visitNoOp() {}

    private fun visitCompound(node: Compound) {
        for (child in node.children) {
            this.visit(child)
        }
    }

    private fun visitAssign(node: Assign) {
        val nameOfVariable = node.left.value
        this.globalVar[nameOfVariable] = this.visit(node.right)!!
    }

    private fun visitVar(node: Var): Int? {
        val valueOfVar: Int?
        val varName = node.value
        if (!this.globalVar.containsKey(varName)) {
            println("ERROR")
        }
        valueOfVar = this.globalVar[varName]
        return valueOfVar
    }

    //
    private fun visitLogOp(node: LogOp) {
        // println("${node.variable.value}:${visitVar(node.variable)}")
        this.outputStr = this.outputStr + "${node.variable.value}:${visitVar(node.variable)} \n"
    }

    private fun visitIfStatement(node: IfStatement) {
        if (visitCondition(node.condition)) {
            visit(node.ifBody)
        }
    }

    private fun visitIfElseStatement(node: IfElseSt) {
        if (visitCondition(node.condition)) {
            visit(node.ifBody)
        } else {
            visit(node.elseBody)
        }
    }

    private fun visitWhileSt(node: WhileSt) {
        while (visitCondition(node.condition)) {
            visit(node.whileBody)
        }
    }

    private fun visitCondition(node: Condition): Boolean {
        val valueLeftCondition = visit(node.leftCondition)
        val valueRightCondition = visit(node.rightCondition)
        val operator = node.op
        if (operator == "==") return valueLeftCondition == valueRightCondition
        if (operator == "!=") return valueLeftCondition != valueRightCondition
        if (operator == ">=") return valueLeftCondition!! >= valueRightCondition!!
        if (operator == "<=") return valueLeftCondition!! <= valueRightCondition!!
        if (operator == ">") return valueLeftCondition!! > valueRightCondition!!
        if (operator == "<") return valueLeftCondition!! < valueRightCondition!!
        return false
    }

    private fun visit(node: AST): Int? {
        when (node.javaClass.simpleName) {
            "BinOp" -> {
                return this.visitBinOp(node as BinOp)
            }
            "Num" -> {
                return this.visitNum(node as Num)
            }
            "UnaryOp" -> {
                return this.visitUnaryOp(node as UnaryOp)
            }
            "NoOp" -> {
                this.visitNoOp()
            }
            "LogOp" -> {
                this.visitLogOp(node as LogOp)
            }
            "IfStatement" -> {
                this.visitIfStatement(node as IfStatement)
            }
            "IfElseSt" -> {
                this.visitIfElseStatement(node as IfElseSt)
            }
            "Compound" -> {
                this.visitCompound(node as Compound)
            }
            "WhileSt" -> {
                this.visitWhileSt(node as WhileSt)
            }
            "Assign" -> {
                this.visitAssign(node as Assign)
            }
            "Var" -> {
                return this.visitVar(node as Var)
            }
        }


        return 0
    }

    fun startAnalyze(): Int {
        val tree = this.parser.parse()
        return this.visit(tree)!!
    }

}