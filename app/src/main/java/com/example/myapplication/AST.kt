package com.example.myapplication

open class AST

class BinOp( var left:AST, var op:Token, var right:AST):AST()

class Num(token:Token):AST(){
    var value:Int= token.value!!.toInt()
}
class Condition(var leftCondition: AST, op:Token,var rightCondition: AST):AST(){
    var op=op.value
}

class WhileSt(var condition: Condition, var whileBody: AST):AST()

class IfStatement(var condition: Condition, var ifBody:AST):AST()

class IfElseSt(var condition: Condition, var ifBody:AST, var elseBody:AST):AST()
class UnaryOp(var op:Token,var expr:AST):AST()

class Compound :AST(){
    var children= mutableListOf<AST>()  //created empty array
}

class Assign(var left: Var, var right: AST):AST()

class LogOp(var variable:Var):AST()
class Var(token:Token):AST(){
    var value=token.value //variable's name
}

class NoOp :AST(){//empty statement
}