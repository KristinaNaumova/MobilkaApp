package com.example.myapplication

class Parser(private var lexer: Lexer) {

    private var currentToken=this.lexer.getNextToken()

    private fun consume(token_type:String)
    {
        if(this.currentToken.type==token_type){
            this.currentToken=this.lexer.getNextToken()
        }
        else
        {
            println("ERROR")
        }
    }

    private fun program():AST{
        val node=this.compoundStatement()
        this.consume(TypeToken.DOT.typeName)
        return node
    }

    private fun compoundStatement():AST{
        this.consume(TypeToken.BEGIN.typeName)
        val nodes=this.statementList()
        this.consume(TypeToken.END.typeName)

        val root=Compound()

        for(node in nodes){
            root.children.add(node)
        }
        return root
    }

    private fun statementList(): MutableList<AST> {
        val node=this.statement()
        val results= mutableListOf<AST>()
        results.add(node)
        while (this.currentToken.type==TypeToken.SEMI.typeName){//после каждого statement  должен стоять SEMI
            this.consume(TypeToken.SEMI.typeName)
            results.add(this.statement())
        } //лист стэйтментов
        if(this.currentToken.type==TypeToken.ID.typeName){
            //ERROR
        }
        return results
    }

    private fun statement():AST{
        val node:AST
        when (this.currentToken.type) {
            TypeToken.BEGIN.typeName -> {
                node=this.compoundStatement()
            }
            TypeToken.ID.typeName -> {

                node=this.assignmentStatement()
            }
            TypeToken.LOG.typeName -> {

                node=this.logStatement()
            }
            TypeToken.WHILE.typeName -> {

                node=this.whileStatement()
            }
            TypeToken.IF.typeName -> {

                node=this.ifStatement()
            }
            TypeToken.IFELSE.typeName -> {

                node=this.ifElseStatement()
            }
            else -> {
                node=this.empty()
            }
        }
        return node
    }

    private fun whileStatement(): AST {
        this.consume(TypeToken.WHILE.typeName)
        val conditionNode = conditionSt()
        val whileBody = this.compoundStatement()
        return WhileSt(conditionNode, whileBody)
    }
    private fun ifElseStatement(): AST {
        this.currentToken
        this.consume(TypeToken.IFELSE.typeName)
        val conditionNode = conditionSt()
        val ifBody = this.compoundStatement()
        this.consume(TypeToken.SEMI.typeName)
        val elseBody = this.compoundStatement()
        return IfElseSt(conditionNode, ifBody, elseBody)
    }

    private fun conditionSt():Condition{
        val leftCondition=this.expr()
        val op:Token=this.currentToken
        this.consume(TypeToken.COMPAREOPERATOR.typeName)
        val rightCondition=this.expr()
        return Condition(leftCondition,op,rightCondition)
    }

    private fun ifStatement(): AST {
        this.currentToken
        this.consume(TypeToken.IF.typeName)
        val conditionNode = conditionSt()
        return IfStatement(conditionNode, compoundStatement())
    }
    private fun logStatement():AST{
        this.currentToken
        this.consume(TypeToken.LOG.typeName)
        if (this.currentToken.type==TypeToken.ID.typeName) {
            return LogOp(variable() as Var)
        }
        return this.empty()
    }

    private fun assignmentStatement(): AST {
        val left = this.variable()
        this.currentToken
        this.consume(TypeToken.ASSIGN.typeName)
        val right = this.expr()
        return Assign(left as Var, right)
    }

    private fun variable():AST{
        val node=Var(this.currentToken)
        this.consume(TypeToken.ID.typeName)
        return node
    }
    private fun empty():AST{
        return NoOp()
    }
    private fun factor():AST
    {
        val token=this.currentToken
        if(token.type==TypeToken.PLUS.typeName) {
            this.consume(TypeToken.PLUS.typeName)
            return UnaryOp(token, factor())
        }
        else if( token.type==TypeToken.MINUS.typeName){
            this.consume(TypeToken.MINUS.typeName)
            return UnaryOp(token,this.factor())
        }
        if(token.type==TypeToken.INTEGER.typeName)
        {
            this.consume(TypeToken.INTEGER.typeName)
            return Num(token)
        }
        else if(token.type==TypeToken.LPAREN.typeName)
        {
            this.consume(TypeToken.LPAREN.typeName)
            val node=this.expr()
            this.consume(TypeToken.RPAREN.typeName      )
            return node
        }
        return this.variable()
    }


    private fun term():AST
    {
        var node=this.factor()
        var token:Token
        while(this.currentToken.type==TypeToken.MUL.typeName||this.currentToken.type==TypeToken.DIV.typeName){
            token=this.currentToken
            if(token.type==TypeToken.MUL.typeName)
            {
                this.consume(TypeToken.MUL.typeName)
            }
            else if(token.type==TypeToken.DIV.typeName)
            {
                this.consume(TypeToken.DIV.typeName)
            }
            node=BinOp(node,token,this.factor())

        }
        return node
    }

    private fun expr():AST
    {
        var node=this.term()
        var token:Token
        while(this.currentToken.type==TypeToken.PLUS.typeName||this.currentToken.type==TypeToken.MINUS.typeName)
        {
            token=this.currentToken
            if(token.type==TypeToken.PLUS.typeName){
                this.consume(TypeToken.PLUS.typeName)
            }
            else if(token.type==TypeToken.MINUS.typeName){
                this.consume(TypeToken.MINUS.typeName)
            }
            node=BinOp(node,token,this.term())

        }
        return node
    }

    fun parse():AST {
        val node=this.program()
        if(this.currentToken.type!=TypeToken.EOF.typeName){
            //ERROR
        }
        return node
    }

}