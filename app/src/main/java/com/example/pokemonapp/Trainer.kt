package com.example.pokemonapp

data class Trainer(val trainerName: String) {
    private var pokemonTeam: PokemonTeam = PokemonTeam()
    private var pokemonCollection: PokemonCollection = PokemonCollection()

}