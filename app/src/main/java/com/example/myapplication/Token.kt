package com.example.myapplication

class Token(var type: String, var value: String?)

enum class TypeToken(val typeName: String) {
    INTEGER("INTEGER"),
    PLUS("PLUS"),
    MINUS("MINUS"),
    MUL("MUL"),
    DIV("DIV"),
    LPAREN("("),
    RPAREN(")"),
    EOF("EOF"),
    BEGIN("BEGIN"),
    END("END"),
    DOT("DOT"),
    ASSIGN("ASSIGN"),
    SEMI("SEMI"),
    ID("ID"),
    LOG("LOG"),
    IF("IF"),
    IFELSE("IFELSE"),
    COMPAREOPERATOR("COMPAREOPERATOR"),
    WHILE("WHILE")
}

val KEYWORDS = mutableMapOf(
    "BEGIN" to "BEGIN",
    "END" to "END",
    "LOG" to "LOG",
    "IF" to "IF",
    "IFELSE" to "IFELSE",
    "WHILE" to "WHILE"
)