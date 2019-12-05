<template>
 <v-layout row>
    <v-flex xs12 sm6 offset-sm3>
      <v-card>
        <v-list>
          <v-list-tile
            v-for="item in categorys"
            :key="item"
            avatar
            @click="getMenuPage(item)"
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
      const baseURI = 'http://35.241.87.161:8080'; 
      this.$http.post(`${baseURI}/getBurgerCategory`, { BRAND:"burgerking" })
      .then((result) => {
        this.categorys = result.data
      })
  },
  data: () =>({
    categorys:[],
    menus:[],
    link:'/burgerKing/menus'
  }),
  methods: {
    getMenuPage(data) {
        this.$router.push({name: "burgerkingMenus", params:{"category": data}})
    }
  }
  
}
</script>

<style>

</style>
