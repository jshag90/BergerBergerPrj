<template>
 <v-layout row>
    <v-flex xs12 sm6 offset-sm3>
      <v-card>
        <v-list>
          <v-list-tile
            v-for="item in categorys"
            :key="item"
            avatar
            @click="getMenu(item)"
          >
             <v-list-tile-action>
              <v-icon color="primary">mdi-chevron-right</v-icon>
            </v-list-tile-action>

            <v-list-tile-content>
              <v-list-tile-title v-text="item"></v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-avatar>
            </v-list-tile-avatar>
          </v-list-tile>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  mounted() {
     const baseURI = 'http://192.168.0.16:8080';
      this.$http.post(`${baseURI}/getBurgerCategory`, { BRAND:"kfc" })
      .then((result) => {
       this.categorys = result.data
      })
  },
  data: () =>({
    categorys:[]
  })
  
}
</script>

<style>

</style>