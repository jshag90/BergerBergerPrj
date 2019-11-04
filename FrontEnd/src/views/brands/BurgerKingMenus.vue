<template>
  <v-card
    class="mx-auto overflow-hidden"
    max-width="500"
    max-height="1150"
  >
     <v-container fluid>
      <v-row dense>
        <v-col
          v-for="item in menus"
          :key="item.NAME"
          :cols="12"
        >
          <v-card  >
             <v-row align="center" justify="center">
            <v-img
              :src="item.IMG"
              class="white--text align-end lighten-2"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              aspect-ratio="1"
              contain
              max-height="177"
            >

             <v-container
                    fill-height
                    fluid
                    pa-2
                  >
                    <v-layout >
                      <v-flex xs10 align-end flexbox>
                        <span class="headline white--text" v-text="item.NAME"></span>
                      </v-flex>
                   </v-layout> 

                   <v-layout >
                      <v-flex xs25 flexbox>
                        <span class="headline white--text" v-text="item.PRICE"></span>
                      </v-flex>
                   </v-layout>


             </v-container>
            </v-img>
             </v-row>
            <v-card-actions>
              <v-spacer></v-spacer>

            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>

</template>

<script>
export default {
   mounted() {
      const baseURI = 'http://localhost:8080'; 
       var category = this.$route.params.category;
       this.$http.post(`${baseURI}/getBurgerMenus`, { BRAND:"burgerking", CATEGORY:category, PRICE_ORDER:"ASC" })
      .then((result) => {
        this.menus = result.data
        console.log(this.menus)
      })
     
      console.log(category)
  },
  data: () =>({
    menus:[]
  })

}
</script>

<style>

</style>