package br.com.uniptcc.brasilqueimadas.core.network.response

import java.math.BigDecimal

class BuscarFocosResponse(val properties: Properties)

class Properties (val latitude: BigDecimal, val longitude: BigDecimal, val municipio: String)
