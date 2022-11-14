package com.example.pokemonapp

class Trainer(trainerName: String) {
    var pokemonTeam: PokemonTeam = PokemonTeam()
    var pokemonCollection: PokemonCollection = PokemonCollection()
    var name : String

    init {
        name = trainerName
    }
}