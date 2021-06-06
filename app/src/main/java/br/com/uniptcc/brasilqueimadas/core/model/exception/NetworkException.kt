package br.com.uniptcc.brasilqueimadas.core.model.exception

class NetworkException : Exception {

    constructor() : super()
    constructor(message : String) : super (message)
}