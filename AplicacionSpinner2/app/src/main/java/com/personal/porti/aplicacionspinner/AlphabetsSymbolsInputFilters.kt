package com.personal.porti.aplicacionspinner

import android.text.InputFilter
import android.text.Spanned

class AlphabetsSymbolsInputFilter(symbols:String) : InputFilter {

    private var mWordPattern: String
    var mLetterPattern:String = "[a-zA-Z.$symbols]"

    init {
        //mLetterPattern = "[a-zA-Z0-9.$symbols ]" // replace if alphanumeric
        mWordPattern = "$mLetterPattern+"
    }

    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int): CharSequence? {
        if(source == ""){
            println("In backspace")
            return source
        }
        if(source.isNotEmpty() && source.toString().matches(mWordPattern.toRegex())){
            return source
        }
        var sourceStr: String
        if(source.isNotEmpty() && !source.toString().matches(mLetterPattern.toRegex())){
            sourceStr = source.toString()
            while(sourceStr.isNotEmpty() && !sourceStr.matches(mWordPattern.toRegex())){
                println(" source --> $source dest ---> $dest")
                if(sourceStr.last().isDigit()) {
                    print("Is digit ")
                    sourceStr = sourceStr.subSequence(0, sourceStr.length - 1).toString()
                }
                else if(!sourceStr.last().toString().matches(mLetterPattern.toRegex())) {
                    print("Is emoji or weird symbols")
                    sourceStr = sourceStr.subSequence(0, sourceStr.length - 1).toString()
                }else
                    break
            }
            return sourceStr
        }
        return source
    }
}