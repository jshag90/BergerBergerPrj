<template>
    <v-card
    class="mx-auto overflow-hidden"
    max-width="500"
    max-height="1150"
  >
     <v-container fluid>
      <v-row dense>
        <v-col
          v-for="card in cards"
          :key="card.title"
          :cols="card.flex"
        >
          <v-card :to="card.to" >
             <v-row align="center" justify="center">
            <v-img
              :src="card.src"
              class="white--text align-end lighten-2"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              aspect-ratio="1"
              contain
              max-height="177"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>
             </v-row>
            <v-card-actions>
              <v-spacer></v-spacer>

              <!-- <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-bookmark</v-icon>
              </v-btn>

              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn> -->
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
    data: () => ({
    drawer: false,
    group: null,
    cards: [
        { title: '버거킹(BurgerKing)', to: '/burgerKing', src: require('../assets/burgerking_mark.gif'), flex: 12 },
        { title: '맥도날드(Mcdonalds)', to: '/macdonald', src:  require('../assets/macdonalds_mark.png'), flex: 12 },
        { title: '케이에프씨(KFC)', to: '/kfc', src: require('../assets/kfc_mark.png'), flex: 12 }
      ]
        })
}
</script>
<style>

</style>
