fun linhaAzul(numColumns: Int , numLines: Int , idxLinha: Int , number: Int , count: Int , startBlue: String ,
              end: String): String {
    var texto = ""
    val quadradoAzul = "$startBlue   $end"
    var countColuna = count
    if (idxLinha == 0) {//Vai escrever a linha superior com as letras
        var letra = 'A'
        while (countColuna < numColumns + 2) {
            if (countColuna == 0 || countColuna == numColumns + 1) {
                texto += quadradoAzul
            } else {
                texto += "$startBlue $letra $end"
                letra++
            }
            countColuna++
        }
    }
    if (idxLinha > numLines) {//Vai escrever a ultima linha
        countColuna = 0
        while (countColuna < numColumns + 2) {
            texto += quadradoAzul
            countColuna++
        }
    }
    if (idxLinha in 1..numLines) {//Vai escrever a linha da esquerda com os numeros
        if (countColuna == 0) {
            texto += "$startBlue $number $end"
        } else if (countColuna == numColumns) {//Vai escrever a linha da direita
            texto += quadradoAzul
        }
    }
    return texto
}

fun buildBoard(numColumns: Int , numLines: Int , showLegend: Boolean = false , showPieces: Boolean = false , pieces:
Array<Pair<String , String>?>): String {
    var countLinhas = 0
    var countColunas: Int
    var text = ""
    val esc: String = Character.toString(27.toChar())
    val startBlue = "$esc[30;44m"
    val startGrey = "$esc[30;47m"//cor cinza
    val startWhite = "$esc[30;30m"//cor branca
    val end = "$esc[0m"//parar de escrever a cor
    var count = 0
    var numero = 0//numero da linha se houver legenda
    var alternadorQuadrado: Int//alterna a sequencia das cores
    while (countLinhas < numLines + 2) {//Ciclo das linhas
        countColunas = 0
        //escreve a linha superior e a da esquerda
        if (showLegend) {//primeira linha legenda e a primeira coluna
            text += linhaAzul(numColumns , numLines , countLinhas , numero , countColunas , startBlue , end)
            numero++
        }
        if (countLinhas != 0 && countLinhas != numLines + 1) {//Se nao estiver fora do tabuleiro
            alternadorQuadrado = if (countLinhas % 2 == 0) {
                0
            } else {
                1
            }//É só trocar o 0 e o 1 para que as cores do
            // tabuleiro tambem troquem excepto as com as peças
            while (countColunas < numColumns) {//Ciclo das colunas
                text += if (alternadorQuadrado % 2 == 1) {//Escreve o resto do tabuleiro
                    "$startWhite " + if (showPieces) {
                        convertStringToUnicode(pieces[count]?.first.toString() , pieces[count]?.second.toString())
                    } else {
                        " "
                    } + " $end"
                } else {
                    "$startGrey " + if (showPieces) {
                        convertStringToUnicode(pieces[count]?.first.toString() , pieces[count]?.second.toString())
                    } else {
                        " "
                    } + " $end"
                }
                countColunas++
                if (showLegend && countColunas == numColumns) {//escreve a ultima coluna
                    text += linhaAzul(numColumns , numLines , countLinhas , numero , countColunas , startBlue , end)
                }
                count++
                alternadorQuadrado++
            }
        }
        countLinhas++
        if ((!showLegend && countLinhas in 2..numLines + 1) || showLegend) {
            text += "\n"
        }
    }
    return text
}

fun createInitialBoard(numColumns: Int , numLines: Int): Array<Pair<String , String>?> {
// Diz onde esta a peças e onde nao tiver nenhuma é null
    return when {
        numColumns == 8 && numLines == 8 -> arrayOf(Pair("T" , "b") ,//tabuleiro predefinido do 8x8
                Pair("H" , "b") , Pair("B" , "b") , Pair("Q" , "b") ,
                Pair("K" , "b") , Pair("B" , "b") , Pair("H" , "b") , Pair("T" , "b") , Pair("P" , "b") ,
                Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") ,
                Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") , null , null , null , null ,
                null , null , null , null , null , null , null ,
                null , null , null , null , null , null , null , null , null , null , null , null , null , null , null ,
                null , null , null , null , null , null ,
                Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") ,
                Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") ,
                Pair("T" , "w") , Pair("H" , "w") , Pair("B" , "w") , Pair("K" , "w") , Pair("Q" , "w") ,
                Pair("B" , "w") , Pair("H" , "w") , Pair("T" , "w"))
        numColumns == 7 && numLines == 7 -> arrayOf(Pair("T" , "b") ,//tabuleiro predefinido do 7x7
                Pair("H" , "b") , Pair("B" , "b") , Pair("K" , "b") ,
                Pair("B" , "b") , Pair("H" , "b") , Pair("T" , "b") , Pair("P" , "b") , Pair("P" , "b") ,
                Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") ,
                Pair("P" , "b") , Pair("P" , "b") , null , null , null , null , null , null , null , null , null , null ,
                null , null , null , null , null , null , null , null , null , null , null , Pair("P" , "w") ,
                Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") ,
                Pair("P" , "w") , Pair("T" , "w") , Pair("H" , "w") , Pair("B" , "w") , Pair("K" , "w") ,
                Pair("B" , "w") , Pair("H" , "w") , Pair("T" , "w"))
        numColumns == 6 && numLines == 7 -> arrayOf(Pair("T" , "b") ,//tabuleiro predefinido do 6x7
                Pair("B" , "b") , Pair("Q" , "b") , Pair("K" , "b") ,
                Pair("B" , "b") , Pair("H" , "b") , Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") ,
                Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") , null ,
                null , null , null , null , null , null , null , null , null , null , null , null , null , null , null ,
                null , null , Pair("P" , "w") , Pair("P" , "w") ,
                Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") , Pair("T" , "w") ,
                Pair("B" , "w") , Pair("K" , "w") , Pair("Q" , "w") , Pair("B" , "w") ,
                Pair("H" , "w"))
        numColumns == 6 && numLines == 6 -> arrayOf(Pair("H" , "b") ,//tabuleiro predefinido do 6x6
                Pair("B" , "b") , Pair("Q" , "b") , Pair("K" , "b") ,
                Pair("B" , "b") , Pair("T" , "b") , Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") ,
                Pair("P" , "b") , Pair("P" , "b") , Pair("P" , "b") , null ,
                null , null , null , null , null , null , null , null , null , null , null , Pair("P" , "w") ,
                Pair("P" , "w") , Pair("P" , "w") , Pair("P" , "w") ,
                Pair("P" , "w") , Pair("P" , "w") , Pair("H" , "w") , Pair("B" , "w") , Pair("K" , "w") ,
                Pair("Q" , "w") , Pair("B" , "w") , Pair("T" , "w"))
        numColumns == 4 && numLines == 4 -> arrayOf(null , null ,//tabuleiro predefinido do 4x4
                Pair("T" , "b") , Pair("B" , "b") , null , null , null ,
                null , null , null , null , null , Pair("T" , "w") , Pair("Q" , "w") , null , null)
        else -> arrayOf()
    }
}

fun createTotalPiecesAndTurn(numColumns: Int , numLines: Int): Array<Int?> {
    // Retorna as peças em jogo de cada um e em que turno ex: (16,16,0)   1-um jogador 0-outro
    return when {
        numColumns == 8 && numLines == 8 -> arrayOf(16 , 16 , 0)//total de pecas do tabuleiro 8x8 e o turno
        numColumns == 7 && numLines == 7 -> arrayOf(14 , 14 , 0)//total de pecas do tabuleiro 7x7 e o turno
        (numColumns == 6 && numLines == 7) || (numColumns == 6 && numLines == 6) -> arrayOf(12 , 12 , 0)
//total de pecas do tabuleiro 6x7,6x6 e o turno
        numColumns == 4 && numLines == 4 -> arrayOf(2 , 2 , 0)//total de pecas do tabuleiro 4x4 e o turno
        else -> arrayOf()
    }
}

fun convertStringToUnicode(piece: String , color: String): String {
    //return ""
    //devolve o Unicode respetivo. Caso a peça e/ou a côr sejam inválidos, deve retornar uma String com um espaço (“ “).
    return when {
        piece == "H" && color == "w" -> "\u2658"//Unicode Horse branco
        piece == "H" && color == "b" -> "\u265E"//Unicode Horse preto
        piece == "T" && color == "w" -> "\u2656"//Unicode Tower branca
        piece == "T" && color == "b" -> "\u265C"//Unicode Tower preta
        piece == "B" && color == "w" -> "\u2657"//Unicode Bishop branco
        piece == "B" && color == "b" -> "\u265D"//Unicode Bishop preto
        piece == "Q" && color == "w" -> "\u2655"//Unicode Queen branca
        piece == "Q" && color == "b" -> "\u265B"//Unicode Queen preta
        piece == "K" && color == "w" -> "\u2654"//Unicode King branco
        piece == "K" && color == "b" -> "\u265A"//Unicode King preto
        piece == "P" && color == "w" -> "\u2659"//Unicode Knight branco
        piece == "P" && color == "b" -> "\u265F"//Unicode Knight preto
        else -> " " //Quando nao é nenhum
    }
}

fun getCoordinates(readText: String?): Pair<Int , Int>? {
    // le as cordenadas e retorna Pair(2,2) nao importa se é A ou a
    if (readText == null || readText.length != 2 || readText[0].toInt() !in 48..57 && (readText[1] !in 'a'..'h'
                    || readText[1] !in 'A'..'H')) {//se for null ou nao ter dois caracteres ou nao tiver um numero e um numero
        return null
    }
    return Pair(readText[0].toInt() - 48 , if (readText[1] in 'A'..'Z') { // return dos valores
        readText[1].toInt() - 64
    } else {
        readText[1].toInt() - 96
    })
}

fun checkRightPieceSelected(pieceColor: String , turn: Int): Boolean = pieceColor == "b" && turn == 1
        || pieceColor == "w" && turn == 0 //se for preto tem de estar no turno 1 se for branco tem de estar no turno 0

fun isCoordinateInsideChess(coord: Pair<Int , Int> , numColumns: Int , numLines: Int):
        Boolean = coord.first in 1..numLines && coord.second in 1..numColumns
//se estiver entre 1 e o numero1
//Colunas/Linhas está dentro do tabuleiro

fun isValidTargetPiece(currentSelectedPiece: Pair<String , String> , currentCoord: Pair<Int , Int> ,
                       targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> , numColumns: Int ,
                       numLines: Int): Boolean {
    //Valida se o movimento da peça é válido. Se sim retorna true e false caso contrário.
    // (Dica: Esta função irá ser chamada dentro da função movePiece).
    return when (currentSelectedPiece.first) {//Verifica se o movimento é valido
        "H" -> isHorseValid(currentCoord , targetCoord , pieces , numColumns , numLines)
        "K" -> isKingValid(currentCoord , targetCoord , pieces , numColumns , numLines)
        "T" -> isTowerValid(currentCoord , targetCoord , pieces , numColumns , numLines)
        "B" -> isBishopValid(currentCoord , targetCoord , pieces , numColumns , numLines)
        "Q" -> isQueenValid(currentCoord , targetCoord , pieces , numColumns , numLines)
        "P" -> isKnightValid(currentCoord , targetCoord , pieces , numColumns , numLines)
        else -> false
    }
}

fun movePiece(pieces: Array<Pair<String , String>?> , numColumns: Int , numLines: Int , currentCoord: Pair<Int , Int> ,
              targetCoord: Pair<Int , Int> , totalPiecesAndTurn: Array<Int>): Boolean {
    //Esta função, irá alterar o valor do argumento pieces e do totalPiecesAndTurno.
    // Só serão alterados caso o movimento das peças sejam válidas. Se forem alterados é retornado true e false
    //caso contrário. (Dica: esta função irá ser chamada dentro da função startNewGame)
    val coordsCurrent = ((currentCoord.first - 1) * (numColumns) + currentCoord.second) - 1
    val coordsTrarget = ((targetCoord.first - 1) * (numColumns) + targetCoord.second) - 1
    val currentSelectedPiece = pieces[coordsCurrent]
    if (isValidTargetPiece(currentSelectedPiece!! , currentCoord , targetCoord , pieces , numColumns , numLines)) {
        if (pieces[coordsTrarget] != null) {//se o target nao for null nem a peça do mesmo turno "come"
            if (totalPiecesAndTurn[2] == 0) {   //uma peca inimiga
                totalPiecesAndTurn[1]--
            } else {
                totalPiecesAndTurn[0]--
            }
        }
        pieces[coordsTrarget] = pieces[coordsCurrent]//substitui a do target pela selecionada
        pieces[coordsCurrent] = null//substitui a coord selecionada por vazio
        if (totalPiecesAndTurn[2] == 0 && (totalPiecesAndTurn[1] != 0 || totalPiecesAndTurn[0] != 0)) {//muda de turno
            totalPiecesAndTurn[2] = 1
        } else {
            totalPiecesAndTurn[2] = 0
        }
        return true
    }
    return false
}

fun startNewGame(whitePlayer: String , blackPlayer: String , pieces: Array<Pair<String , String>?> ,
                 totalPiecesAndTurn: Array<Int?> , numColumns: Int , numLines: Int , showLegend: Boolean = false ,
                 showPieces: Boolean = false) {
    //array para (pecasB,pecasP,turno)  var Jogo=()
    // se a resposta for m return
    //As coordenadas submetidas devem estar dentro dos limites do tabuleiro
    //As coordenadas de partida têm obrigatoriamente de conter uma peça da cor do jogador
    //O movimento das peças devem ser respeitadas
    //A coordenada destino não poderá conter uma peça da mesma côr do jogador que está a jogar
    //A peça escolhida para mover não for da mesma côr das peças do jogador
    //Qualquer erro usar :Invalid response. sem \n
    //é mostrado o tabuleiro
    //e é onde pede constantemente para cada jogador colocar as coordenadas de partida e
    // de origem.Também é aqui que dá a opção de retornar ao menu principal,
    //clicando na tecla “m”. É de notar que os argumentos whitePlayer e blackPlayer são os nomes dados pelos utilizador,
    // em que o whitePlayer é o nome do 1º jogador e o blackPlayer é o nome do 2º jogador.
    //1A 1B 1C 1D 1E 2A 2B 2C 2D 2E 3A 3B 3C 3D 3E 4A 4B 4C 4D 4E
    val error = "Invalid response."
    var totalPiecesAndTurnSemNulls = arrayOf(0 , 0 , 0)
    if (totalPiecesAndTurn.contentToString() != "[]" && totalPiecesAndTurn[0] != null//se tiver nulls fica 0,0,0
            && totalPiecesAndTurn[1] != null && totalPiecesAndTurn[2] != null) {
        totalPiecesAndTurnSemNulls = totalPiecesAndTurn as Array<Int>//convert para um array de ints
    }
    if (totalPiecesAndTurnSemNulls.contentToString() == "[0, 0, 0]") {//e se tiver 0,0,0 dá return e volta para o menu
        return
    }

    do {
        println(buildBoard(numColumns , numLines , showLegend , showPieces , pieces))
        //ecreve com o nome da pessoa dependendo do turno em que esta
        println(if (totalPiecesAndTurn[2] == 1) {
            blackPlayer
        } else {
            whitePlayer
        } + ", choose a piece (e.g 2D).")
        println("Menu-> m;\n")

        val peca = readLine().toString()
        if (peca.length == 2 && peca[0].toString() in "1"..numLines.toString() && (peca[1] in 'a'..'a' + (numColumns - 1)
                        || peca[1] in 'A'..'A' + (numColumns - 1))) {
            //tem de ter 2 caracteres e o numero da linha tem estar entre 1 e o numero de linhas e a letra das colunas tem de estar entre
            //a/A e a colunas final
            val current = getCoordinates(peca)//converte as coordenadas para um Pair<Int,Int>
            val coordsCurrent = ((current!!.first - 1) * numColumns + current.second) - 1//Calcula a posicao no array pieces
            if (isCoordinateInsideChess(current , numColumns , numLines)
                    && checkRightPieceSelected(if (coordsCurrent !in 0..numColumns * numLines
                            || pieces[coordsCurrent]?.second == null) {
                        ""
                    } else {
                        pieces[coordsCurrent]!!.second
                    } ,
                            totalPiecesAndTurn[2] ?: 0)) {

                println(if (totalPiecesAndTurn[2] == 1) {
                    blackPlayer
                } else {
                    whitePlayer
                }
                        + ", choose a target piece (e.g 2D).")
                println("Menu-> m;\n")
                //ecreve com o nome da pessoa dependendo do turno em que esta
                val destino = readLine().toString()
                if (destino.length == 2 && destino[0].toString() in "1"..numLines.toString() && (destino[1]
                                in 'a'..'a' + (numColumns - 1) || destino[1] in 'A'..'A' + (numColumns - 1))) {
                    //tem de ter 2 caracteres e o numero da linha tem estar entre 1 e o numero de linhas e a letra das colunas tem de estar entre
                    //a/A e a colunas final
                    val target = getCoordinates(destino)
                    val coordsTarget = ((target!!.first - 1) * numColumns + target.second) - 1

                    if (isCoordinateInsideChess(target , numColumns , numLines)
                            && !checkRightPieceSelected(if (coordsTarget !in 0..numColumns * numLines
                                    || pieces[coordsTarget]?.second == null) {
                                ""
                            } else {
                                pieces[coordsTarget]!!.second
                            } ,
                                    totalPiecesAndTurn[2] ?: 0)) {

                        //se estiver dentro do tabuleiro e a cor da peca target for diferente da selecionada
                        if (!movePiece(pieces , numColumns , numLines , current , target ,
                                        totalPiecesAndTurnSemNulls)) {// se nao houver movimento dá erro
                            println(error)
                        }
                        transformaPeao(pieces , numColumns , numLines)
                        if (reiComido(pieces) != 2) {
                            return
                        }

                    } else {
                        println(error)
                    }
                } else {
                    if (destino == "m" || destino == "M") {
                        return
                    }
                    println(error)
                }
            } else {
                println(error)
            }
        } else {
            if (peca == "m" || peca == "M") {
                return
            }
            println(error)
        }
    } while ((totalPiecesAndTurn[0] != 0 && totalPiecesAndTurn[1] != 0))//Só quando um dos jogadores ficar sem pecas
}

fun isHorseValid(currentCoord: Pair<Int , Int> , targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> ,
                 numColumns: Int , numLines: Int): Boolean {
    //Dado as coordenadas do cavalo (currentCoord), as coordenadas de destino (targetCoord) e as peças do tabuleiro
    // (pieces) Esta função não deve alterar o tabuleiro, apenas verificar se o movimento é válido
    val coordsCurrent = ((currentCoord.first - 1) * (numColumns) + currentCoord.second) - 1
    val coordsTrarget = ((targetCoord.first - 1) * (numColumns) + targetCoord.second) - 1
    return ((currentCoord.first + 2 == targetCoord.first && currentCoord.second + 1 == targetCoord.second)
            || (currentCoord.first + 1 == targetCoord.first && currentCoord.second + 2 == targetCoord.second)
            || (currentCoord.first + 1 == targetCoord.first && currentCoord.second - 2 == targetCoord.second)
            || (currentCoord.first + 2 == targetCoord.first && currentCoord.second - 1 == targetCoord.second)
            || (currentCoord.first - 2 == targetCoord.first && currentCoord.second + 1 == targetCoord.second)
            || (currentCoord.first - 1 == targetCoord.first && currentCoord.second + 2 == targetCoord.second)
            || (currentCoord.first - 1 == targetCoord.first && currentCoord.second - 2 == targetCoord.second)
            || (currentCoord.first - 2 == targetCoord.first && currentCoord.second - 1 == targetCoord.second))
            && ((pieces[coordsCurrent]?.second == "w" && pieces[coordsTrarget]?.second != "w")
            || (pieces[coordsCurrent]?.second == "b" && pieces[coordsTrarget]?.second != "b"))
}

fun isKingValid(currentCoord: Pair<Int , Int> , targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> ,
                numColumns: Int , numLines: Int): Boolean {
    //Dado as coordenadas do rei (currentCoord), as coordenadas de destino (targetCoord) e as peças do tabuleiro(pieces)
    //Esta função não deve alterar o tabuleiro,apenas verificar se o movimento é válido.
    val coordsCurrent = ((currentCoord.first - 1) * (numColumns) + currentCoord.second) - 1
    val coordsTrarget = ((targetCoord.first - 1) * (numColumns) + targetCoord.second) - 1
    return ((currentCoord.first + 0 == targetCoord.first || currentCoord.first + 1 == targetCoord.first
            || currentCoord.first - 1 == targetCoord.first) && (currentCoord.second + 0 == targetCoord.second
            || currentCoord.second + 1 == targetCoord.second || currentCoord.second - 1 == targetCoord.second))
            && ((pieces[coordsCurrent]?.second == "w" && pieces[coordsTrarget]?.second != "w")
            || (pieces[coordsCurrent]?.second == "b" && pieces[coordsTrarget]?.second != "b"))
}

fun isTowerValid(currentCoord: Pair<Int , Int> , targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> ,
                 numColumns: Int , numLines: Int): Boolean {
    //Dado as coordenadas da Torre(currentCoord), as coordenadas de destino(targetCoord) e as peças do tabuleiro(pieces)
    //Esta função não deve alterar o tabuleiro, apenas verificar se o movimento é válido.
   val coordsCurrent = ((currentCoord.first - 1) * (numColumns) + currentCoord.second) - 1
    val coordsTrarget = ((targetCoord.first - 1) * (numColumns) + targetCoord.second) - 1
    if ((currentCoord.first == targetCoord.first && currentCoord.second != targetCoord.second
                    || currentCoord.first != targetCoord.first && currentCoord.second == targetCoord.second)
            && ((pieces[coordsCurrent]?.second == "w" && pieces[coordsTrarget]?.second != "w")
                    || (pieces[coordsCurrent]?.second == "b" && pieces[coordsTrarget]?.second != "b"))) {
        var posaux = Pair(currentCoord.first , currentCoord.second)
        do {
            if (currentCoord.first == targetCoord.first) {
                posaux = Pair(currentCoord.first , if (currentCoord.second < targetCoord.second) {
                    posaux.second + 1
                } else {
                    posaux.second - 1
                })
                val coordsVer = ((posaux.first - 1) * (numColumns) + posaux.second) - 1
                if (posaux != targetCoord && isCoordinateInsideChess(posaux , numColumns , numLines) && pieces[coordsVer] != null) {
                    return false
                }

            } else {
                posaux = Pair(if (currentCoord.first < targetCoord.first) {
                    posaux.first + 1
                } else {
                    posaux.first - 1
                } , currentCoord.second)
                val coordsVer = ((posaux.first - 1) * (numColumns) + posaux.second) - 1
                if (posaux != targetCoord && isCoordinateInsideChess(posaux , numColumns , numLines) && pieces[coordsVer] != null) {
                    return false
                }
            }

        } while (posaux != targetCoord)
        return true
    }
    return false
   /* var count=1
    while(count<=4)
    {
        if(currentCoord.first==targetCoord.first+count || currentCoord.first==targetCoord.first-count )
            return true
        count++
    }
    return false*/
}

fun isBishopValid(currentCoord: Pair<Int , Int> , targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> ,
                  numColumns: Int , numLines: Int): Boolean {
    //Dado as coordenadas do Bispo(currentCoord), as coordenadas de destino(targetCoord) e as peças do tabuleiro(pieces)
    //Esta função não deve alterar o tabuleiro, apenas verificar se o movimento é válido.
    val coordsFirst = if (currentCoord.first - targetCoord.first < 0) {
        (currentCoord.first - targetCoord.first) * -1
    } else {
        currentCoord.first - targetCoord.first
    }
    val coordsSecond = if (currentCoord.second - targetCoord.second < 0) {
        (currentCoord.second - targetCoord.second) * -1
    } else {
        currentCoord.second - targetCoord.second
    }

    val coordsCurrent = ((currentCoord.first - 1) * (numColumns) + currentCoord.second) - 1
    val coordsTrarget = ((targetCoord.first - 1) * (numColumns) + targetCoord.second) - 1
    var posaux = Pair(currentCoord.first , currentCoord.second)
    if ((coordsFirst == coordsSecond && ((pieces[coordsCurrent]?.second == "w" && pieces[coordsTrarget]?.second != "w")
                    || (pieces[coordsCurrent]?.second == "b" && pieces[coordsTrarget]?.second != "b")))) {
        do {
            when {
                currentCoord.first > targetCoord.first && currentCoord.second < targetCoord.second -> {
                    posaux = Pair(posaux.first - 1 , posaux.second + 1)
                }
                currentCoord.first > targetCoord.first && currentCoord.second > targetCoord.second -> {
                    posaux = Pair(posaux.first - 1 , posaux.second - 1)
                }
                currentCoord.first < targetCoord.first && currentCoord.second < targetCoord.second -> {
                    posaux = Pair(posaux.first + 1 , posaux.second + 1)
                }
                currentCoord.first < targetCoord.first && currentCoord.second > targetCoord.second -> {
                    posaux = Pair(posaux.first + 1 , posaux.second - 1)
                }
            }
            val coordsVer = ((posaux.first - 1) * (numColumns) + posaux.second) - 1
            if (posaux != targetCoord && isCoordinateInsideChess(posaux , numColumns , numLines) && pieces[coordsVer] != null) {
                return false
            }
        } while (posaux != targetCoord)
        return true
    }
    return false
}

fun isQueenValid(currentCoord: Pair<Int , Int> , targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> ,
                 numColumns: Int , numLines: Int): Boolean {
    return isBishopValid(currentCoord , targetCoord , pieces , numColumns , numLines) || isTowerValid(currentCoord ,
            targetCoord , pieces , numColumns , numLines)
//Dado as coordenadas da Rainha (currentCoord), as coordenadas de destino (targetCoord) e as peças do tabuleiro (pieces)
//Esta função não deve alterar o tabuleiro, apenas verificar se o movimento é válido.
}

fun isKnightValid(currentCoord: Pair<Int , Int> , targetCoord: Pair<Int , Int> , pieces: Array<Pair<String , String>?> ,
                  numColumns: Int , numLines: Int): Boolean {
    //Dado as coordenadas do Peão (currentCoord), as coordenadas de destino (targetCoord), as peças do tabuleiro(pieces)
    //o turno actual (totalPiecesAndTurn)
    //Esta função não deve alterar o tabuleiro,apenas verificar se o movimento é válido
    val coordsCurrent = ((currentCoord.first - 1) * (numColumns) + currentCoord.second) - 1
    val coordsTrarget = ((targetCoord.first - 1) * (numColumns) + targetCoord.second) - 1
   return ((currentCoord.first+1 == targetCoord.first && (currentCoord.second+1==targetCoord.second
           || currentCoord.second-1==targetCoord.second) && pieces[coordsTrarget]?.second=="w")
           || ((currentCoord.first-1 == targetCoord.first && (currentCoord.second+1==targetCoord.second
           || currentCoord.second-1==targetCoord.second) && pieces[coordsTrarget]?.second=="b"))
           || (pieces[coordsCurrent]?.second=="w" && currentCoord.first-1==targetCoord.first
           && currentCoord.second==targetCoord.second && pieces[coordsTrarget]?.second==null)
           || (pieces[coordsCurrent]?.second=="b" && currentCoord.first+1==targetCoord.first
           && currentCoord.second==targetCoord.second && pieces[coordsTrarget]?.second==null))
}

fun reiComido(pieces: Array<Pair<String , String>?>): Int {
    var count = 0
    var temReiB = false
    var temReiP = false
    while (count < pieces.size) {
        if (pieces[count]?.first == "K") {
            when (pieces[count]?.second) {
                "b" -> {
                    temReiP = true
                }
                "w" -> {
                    temReiB = true
                }
            }
        }
        count++
    }
    return when {
        temReiB && temReiP -> 2
        temReiB && !temReiP -> 0
        !temReiB && temReiP -> 1
        else -> 2
    }
}

fun transformaPeao(pieces: Array<Pair<String , String>?> , numColumns: Int , numLines: Int) {
    var count = 0
    val linhaCima = 0..numColumns - 1
    val linhaBaixo = numLines * (numColumns - 1)..numColumns * numLines - 1
    var peca = ""
    while (count < pieces.size) {
        if (pieces[count] == Pair("P" , "b") && count in linhaBaixo) {
            do {
                println("Escolha uma peça (Q,B,H,T):")
                peca = readLine().toString()
                if (peca.toUpperCase() != "Q" && peca.toUpperCase() != "B" && peca.toUpperCase() != "H" && peca.toUpperCase() != "T")
                    println("Invalid response.")
            } while (peca.toUpperCase() != "Q" && peca.toUpperCase() != "B" && peca.toUpperCase() != "H" && peca.toUpperCase() != "T")
            pieces[count] = Pair(peca.toUpperCase() , "b")
        } else if (pieces[count] == Pair("P" , "w") && count in linhaCima) {
            do {
                println("Escolha uma peça (Q,B,H,T):")
                peca = readLine().toString()
                if (peca.toUpperCase() != "Q" && peca.toUpperCase() != "B" && peca.toUpperCase() != "H" && peca.toUpperCase() != "T")
                    println("Invalid response.")
            } while (peca.toUpperCase() != "Q" && peca.toUpperCase() != "B" && peca.toUpperCase() != "H" && peca.toUpperCase() != "T")
            pieces[count] = Pair(peca.toUpperCase() , "w")
        }
        count++
    }
}

fun main() {
    println("Welcome to the Chess Board Game!")
    do {//Ciclo para estar sempre a jogar ate escrever 0
        println(buildMenu())//print das opcoes
        val opcaoMenu = readLine()?.toIntOrNull()
        val erro = "Invalid response."
        var numeroColunas: String
        var numeroLinhas: String
        var temLegend: String
        var temPieces: String
        var nomeJogador1: String
        var nomeJogador2: String
        when (opcaoMenu) {
            1 -> {
                do {//Ciclo para ver se o nome que e escrito esta correto
                    println("First player name?\n")
                    nomeJogador1 = readLine().toString()
                    if (!checkName(nomeJogador1)) {
                        println(erro)
                    }
                } while (!checkName(nomeJogador1))

                do {//Ciclo para ver se o nome que e escrito esta correto
                    println("Second player name?\n")
                    nomeJogador2 = readLine().toString()
                    if (!checkName(nomeJogador2)) {
                        println(erro)
                    }
                } while (!checkName(nomeJogador2))

                do {
                    println("How many chess columns?\n")
                    numeroColunas = readLine().toString()
                    if (!checkIsNumber(numeroColunas) || numeroColunas.toInt() !in 4..8) {//Se nao for numero ou
                        println(erro)// for menor que 4  e maior que 8 da erro
                    }
                    println("How many chess lines?\n")
                    numeroLinhas = readLine().toString()
                    if (!checkIsNumber(numeroLinhas) || numeroLinhas.toInt() !in 4..8) {
                        println(erro)//Se nao for numero ou for menor que 4  e maior que 8 da erro
                    }
                } while (!checkIsNumber(numeroLinhas) || !checkIsNumber(numeroColunas)
                        || createTotalPiecesAndTurn(numeroColunas.toInt() , numeroLinhas.toInt()).contentToString() == "[]")//ver se o que contem é vazio

                val pecas: Array<Pair<String , String>?> = createInitialBoard(numeroColunas.toInt() , numeroLinhas.toInt())

                do {//Ciclo para ver se a resposta de querer legenda ou nao esta correta
                    println("Show legend (y/n)?\n")
                    temLegend = readLine().toString()
                    if (showChessLegendOrPieces(temLegend) == null) {
                        println(erro)
                    }
                } while (showChessLegendOrPieces(temLegend) == null)

                do {//Ciclo para ver se a resposta de querer pecas ou nao esta correta
                    println("Show pieces (y/n)?\n")
                    temPieces = readLine().toString()
                    if (showChessLegendOrPieces(temPieces) == null) {
                        println(erro)
                    }
                } while (showChessLegendOrPieces(temPieces) == null)

                val totalPiecesAndTurn = createTotalPiecesAndTurn(numeroColunas.toInt() , numeroLinhas.toInt())
                //Comeca o jogo
                startNewGame(nomeJogador1 , nomeJogador2 , pecas , totalPiecesAndTurn , numeroColunas.toInt() ,
                        numeroLinhas.toInt() , showChessLegendOrPieces(temLegend)
                        ?: false , showChessLegendOrPieces(temPieces) ?: false)

                //Só se nao houver nenhuma peca é que diz congrats
                if (totalPiecesAndTurn[0] == 0 || totalPiecesAndTurn[1] == 0 || reiComido(pecas) != 2) {////Dizer Congrats! $nome wins!
                    //Independentemente do turno escreve o nome e diz que ganhou
                    println("Congrats! " + if (totalPiecesAndTurn[1] == 0 || reiComido(pecas) == 0) {
                        nomeJogador1
                    } else {
                        nomeJogador2
                    } + " wins!")
                }
            }
            2 -> return
        }
    } while (opcaoMenu != 2) //se a opcao for diferente de 0 o programa que continua
}

fun buildMenu(): String = "1-> Start New Game;\n2-> Exit Game.\n"//Opcao do menu

fun showChessLegendOrPieces(message: String): Boolean? {
    if (message == "y" || message == "Y") {
        return true
    }
    if (message == "n" || message == "N") {
        return false
    }
    return null//Se nao for y/Y nem n/N
}

fun checkName(number: String): Boolean {
    var idx = 0
    var eMaiuscula = false
    var espaco = 0
    while (idx < number.length) {//Ciclo para percorrer a palavra
        if (number[idx] == ' ') {//Se o caracter for espaco
            espaco++//adiciona um a quantidade de espacos da palavra
            eMaiuscula = number[0] in 'A'..'Z' && (if (idx != number.length - 1) number[idx + 1] in 'A'..'Z' else false)
            //Se a 1º letra for maiuscula e a letra a seguir do espaço tambem for e maiuscula
        }
        idx++
    }
    return eMaiuscula && espaco == 1//Se so houver um espaco e as duas palavras comecarem com maiuscula entao esta certo
}

fun checkIsNumber(number: String): Boolean = number.toIntOrNull() != null //Se na conversao der null quer dizer que
// nao e numero

