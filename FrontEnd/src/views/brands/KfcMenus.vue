<template>
  <v-card
    class="mx-auto overflow-hidden"
    max-width="500"
  >
    <v-container
          fluid
          grid-list-md
        >

        <v-layout row wrap>
            <v-flex
              v-for="item in menus"
               :key="item.NAME"
               v-bind="{ [`xs12`]: true }"
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

                   <v-layout  fill-height > 
                      <v-flex xs20 align-center flexbox>
                        <span class="headline white--text" v-text="item.NAME"></span> 
                      </v-flex>
                   </v-layout>    

             </v-container>
             
            </v-img>
            <v-card-actions>
              <v-spacer></v-spacer>
              <span class="headline" v-text="priceFormatter(item.PRICE)"></span>
            </v-card-actions>
          </v-card>
            </v-flex>
            </v-layout>

             <v-fab-transition >
        <v-btn
          @click="callGetBurgerMenusAxios('DESC')"
          color="primary"
          dark
          fab
          fixed
          bottom
          right
          v-if="this.showAscBtn"
        >
          <v-icon>mdi-sort-ascending</v-icon>
        </v-btn>
      </v-fab-transition>

      <v-fab-transition >
        <v-btn
          @click="callGetBurgerMenusAxios('ASC')"
          color="primary"
          dark
          fab
          fixed
          bottom
          right
          v-if="this.showDescBtn"
        >
          <v-icon>mdi-sort-descending</v-icon>
        </v-btn>
      </v-fab-transition>

    </v-container>
  </v-card>
</template>

<script>
export default {
   mounted() {
      let param = new Object();
      param.isVisible = "1";
      param.link = "/kfc";
      this.$emit("isVisibleBackBtn",param)
      this.callGetBurgerMenusAxios('ASC')
  },
  data: () =>({
    menus:[],
    showDescBtn: true,
    showAscBtn: false
  }),
  methods: {
    priceFormatter(data){
      return data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
    },
    callGetBurgerMenusAxios(order){
      const baseURI = 'http://35.241.87.161:8080'; 
       var category = this.$route.params.category;
       this.$http.post(`${baseURI}/getBurgerMenus`, { BRAND:"kfc", CATEGORY:category, PRICE_ORDER:order})
      .then((result) => {
        this.menus = result.data
      })

      
      if(order === 'ASC'){
          this.showAscBtn = true
           this.showDescBtn = false
      }else{
          this.showAscBtn = false
          this.showDescBtn = true
      }

    }
  },
  watch: {
    $route(to, from) {
       this.callGetBurgerMenusAxios('ASC')
    }
  }

}
</script>
