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
    </v-container>
  </v-card>
</template>

<script>
export default {
  props: { visible: Boolean },
   mounted() {
    let param = new Object();
     param.isVisible = "1";
     param.link = "/macdonald";
     this.$emit("isVisibleBackBtn",param)
     this.callGetBurgerMenusAxios()
  },
  data: () =>({
    menus:[]
  }),
  methods: {
    priceFormatter(data){
      return data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")+"원";
    },
     callGetBurgerMenusAxios(){
       const baseURI = 'http://35.241.87.161:8080'; 
       var category = this.$route.params.category;
       this.$http.post(`${baseURI}/getBurgerMenus`, { BRAND:"macdonald", CATEGORY:category, PRICE_ORDER:"ASC" })
      .then((result) => {
        this.menus = result.data
      })
     }
  },
  watch: {
    $route(to, from) {
       this.callGetBurgerMenusAxios()
    }
  }

}
</script>