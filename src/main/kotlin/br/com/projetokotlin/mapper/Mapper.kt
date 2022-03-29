package br.com.projetokotlin.mapper


interface Mapper<T, U> {

    fun map(t: T): U
}