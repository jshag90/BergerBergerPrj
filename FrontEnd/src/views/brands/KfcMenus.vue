<template>
  <v-card
    class="mx-auto overflow-hidden"
    max-width="500"
    max-height="1150"
  >
    <v-container
          fluid
          grid-list-md
        >

      <!-- <v-row dense> -->
        <!-- <v-col
          v-for="item in menus"
          :key="item.NAME"
          :cols="12"
        > -->

        <v-layout row wrap>
             <!-- <v-row align="center" justify="center"> -->
            <v-flex
              v-for="item in menus"
               :key="item.NAME"
            >

          <v-card>
            <v-img
              :src="item.IMG"
              gradient="to top, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              contain
              height="200px"
            >

             <v-container
                    fill-height
                    fluid
                    pa-2
                  >

                  <v-layout fill-height>
                      <v-flex xs12 align-end flexbox>
                        <span class="headline white--text" v-text="item.NAME"></span>
                      </v-flex>
                    </v-layout>

                    <!-- <v-layout > -->
                      <!-- <v-flex xs7 align-center flexbox> -->
                        <!-- <span class="headline white--text" v-text="item.NAME"></span> -->
                        <!-- <span class="white--text" v-text="priceFormatter(item.PRICE)"></span> -->
                      <!-- </v-flex> -->
                   <!-- </v-layout>  -->

                   <!-- <v-layout >
                      <v-flex xs25 align-center flexbox>
                        <span class="headline white--text" v-text="priceFormatter(item.PRICE)"></span>
                      </v-flex>
                   </v-layout> -->


             </v-container>
            </v-img>
             <!-- </v-row> -->
            <v-card-actions>
              <v-spacer></v-spacer>
              <span class="headline" v-text="priceFormatter(item.PRICE)"></span>
            </v-card-actions>
          </v-card>
            </v-flex>
            </v-layout>
        <!-- </v-col> -->
      <!-- </v-row> -->
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
      })
  },
  data: () =>({
    menus:[]
  }),
  methods: {
    priceFormatter(data){
      return data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
    }
  }

}
</script>

<style>

</style>

<style>

</style>