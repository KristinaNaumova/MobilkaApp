package com.example.myapplication

class Lexer(private var text:String) {
    private var pos=0
    private var currentChar=this.text[this.pos]

    private fun nextPos()
    {
        this.pos+=1
        if(this.pos>this.text.length-1)
        {
            this.currentChar=0.toChar()
        }
        else
        {
            this.currentChar=this.text[this.pos]
        }
    }

    private fun skipWSpaces()
    {
        while (this.pos<this.text.length && this.currentChar.isWhitespace()){
            this.nextPos()
        }
    }

    private fun isNumber():String{
        var result =""
        while(this.currentChar.isDigit()){
            result+=this.currentChar
            this.nextPos()
        }
        return result
    }


    private fun id():Token{
        val token:Token
        var result:String=""+this.currentChar
        this.nextPos()
        while (this.currentChar.isLetter()||this.currentChar.isDigit()){
            result += this.currentChar
            this.nextPos()
        }

        token = if(KEYWORDS.containsKey(result)) {
            Token(result,KEYWORDS[result])
        } else {
            Token(TypeToken.ID.typeName,result)
        }
        return token
    }
    fun getNextToken():Token
    {
        while (this.pos<this.text.length) {

            if(this.currentChar.isWhitespace())
                this.skipWSpaces()
            if(this.currentChar.isLetter())
            {
                return this.id()
            }
            else if(this.pos<this.text.length-1&&(this.currentChar=='>'||this.currentChar=='<'||this.text[pos]=='='&&this.text[pos+1]=='='||this.text[pos]=='!'&&this.text[pos+1]=='='||this.text[pos]=='>'&&this.text[pos+1]=='='||this.text[pos]=='<'&&this.text[pos+1]=='='))
            {
                val operator:String
                if(this.text[pos]=='='&&this.text[pos+1]=='='||this.text[pos]=='!'&&this.text[pos+1]=='='||this.text[pos]=='>'&&this.text[pos+1]=='='||this.text[pos]=='<'&&this.text[pos+1]=='='){

                    operator=this.text[pos]+this.text[pos+1].toString()
                    this.nextPos()
                    this.nextPos()
                }
                else{
                    operator=this.currentChar.toString()
                    this.nextPos()
                }

                return  Token(TypeToken.COMPAREOPERATOR.typeName,operator)
            }
            else if(this.currentChar=='='){
                this.nextPos()
                return Token(TypeToken.ASSIGN.typeName,"=")
            }
            else if(this.currentChar==';'){
                this.nextPos()
                return Token(TypeToken.SEMI.typeName,";")
            }
            else if(this.currentChar=='.'){
                this.nextPos()
                return Token(TypeToken.DOT.typeName,".")
            }
            else if(this.currentChar.isDigit())
            {
                return Token(TypeToken.INTEGER.typeName,this.isNumber())
            }
            else if(this.currentChar=='+')
            {
                this.nextPos()
                return Token(TypeToken.PLUS.typeName,"+")
            }
            else if(this.currentChar=='-')
            {
                this.nextPos()
                return  Token(TypeToken.MINUS.typeName,"-")
            }
            else if(this.currentChar=='/')
            {
                this.nextPos()
                return Token(TypeToken.DIV.typeName,"/")
            }
            else if(this.currentChar=='*')
            {
                this.nextPos()
                return  Token(TypeToken.MUL.typeName,"*")
            }
            else if(this.currentChar=='(')
            {
                this.nextPos()
                return  Token(TypeToken.LPAREN.typeName,"(")
            }
            else if(this.currentChar==')')
            {
                this.nextPos()
                return  Token(TypeToken.RPAREN.typeName,")")
            }

        }
        return Token(TypeToken.EOF.typeName, "")
    }




}