package com.ifarm.porosh.data.local.dbState

sealed class DBState {
    object Idle : DBState()
    object Loading : DBState()
    object Success : DBState()
    data class Error(val msg: String) : DBState()
}