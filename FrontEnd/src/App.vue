<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" fixed app >
      <v-toolbar color="primary" >
        <v-toolbar-title  class="white--text"> 
        </v-toolbar-title>
      </v-toolbar>
      <v-list>
        <v-list-group
          v-for="item in items"
          :key="item.title"
          v-model="item.active"
          no-action
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-content>
                <v-list-tile-title>{{ item.title }}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
          <v-list-tile
            v-for="subItem in item.subItems"
            :key="subItem.title"
            :to="subItem.to"
          >
            <v-list-tile-content>
              <v-list-tile-title>{{ subItem.title }}</v-list-tile-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-icon>{{ subItem.icon }}</v-icon>
            </v-list-tile-action>
          </v-list-tile>

        </v-list-group>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar color="primary" >
      <v-toolbar-side-icon @click="drawer = !drawer" class="white--text"></v-toolbar-side-icon>
      <v-toolbar-title class="white--text">버거버거</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
         <v-btn color="primary" fab small dark @click="goToCategory()" v-if=visible >
                  <span class="group pa-2">
                    <v-icon>mdi-arrow-left-bold</v-icon>
                  </span>
        </v-btn>
        <v-btn color="primary" fab small dark href="/">
                  <span class="group pa-2">
                    <v-icon>mdi-home</v-icon>
                  </span>
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>

    <v-content >
      <router-view @isVisibleBackBtn="isVisibleBackBtn" :orderBy="this.orderBy"/>
    </v-content>
  </v-app>
</template>

<script>
import BurgerKingMenus from './views/brands/BurgerKingMenus.vue'
import BurgerKing from './views/brands/BurgerKing.vue'
import KfcMenus from './views/brands/KfcMenus.vue'
import Kfc from './views/brands/Kfc.vue'
import MacdonaldsMenus from './views/brands/MacdonaldsMenus.vue'
import Macdonalds from './views/brands/Macdonalds.vue'

export default {
  name: 'App',
  components: {
    BurgerKingMenus,
    BurgerKing,
    KfcMenus,
    Kfc,
    MacdonaldsMenus,
    Macdonalds
  },
  data () {
    return {
      fab: false,
      hidden: false,
      drawer: false,
      visible: false,
      backBtnUrl: '',
      baseURI: 'http://localhost:8080',
      orderBy: '',
      items: [
        {
          icon: 'mdi-alert-box',
          title: '버거킹(BurgerKing)',
          active: false,
          subItems: [
            {
              title: '사이드',
              to:'/burgerKing/사이드'
            },
            {
              title: '스폐셜',
              to:'/burgerKing/스폐셜'
            },
            {
              title: '와퍼&버거',
              to:'/burgerKing/와퍼&버거'
            },
            {
              title: '음료',
              to:'/burgerKing/음료'
            },
            {
              title: '치킨버거',
              to:'/burgerKing/치킨버거'
            },
            {
              title: '프리미엄',
              to:'/burgerKing/프리미엄'
            }
          ]
        },
        {
          icon: 'mdi-alert-box',
          title: '맥도날드(Mcdonalds)',
          active: false,
          subItems: [
             {
              title: '디저트',
              to:'/macdonald/디저트'
            },
            {
              title: '버거&세트',
              to:'/macdonald/버거&세트'
            },
            {
              title: '스낵&사이드',
              to:'/macdonald/스낵&사이드'
            },
            {
              title: '음료',
              to:'/macdonald/음료'
            },
            {
              title: '해피밀®',
              to:'/macdonald/해피밀®'
            }
          ]
        },
        {
          icon: 'mdi-alert-box',
          title: '케이에프씨(KFC)',
          active: false,
          subItems: [
            {
              title: '버거&세트',
              to:'/kfc/버거&세트'
            },
            {
              title: '스낵&사이드',
              to:'/kfc/스낵&사이드'
            },
            {
              title: '음료',
              to:'/kfc/음료'
            },
            {
              title: '치킨&세트',
              to:'/kfc/치킨&세트'
            }
          ]
        }
      ]
    }
  },
  methods: {
    goToCategory(data) {
        this.$router.push(this.backBtnUrl)
        this.visible = false
    },
    isVisibleBackBtn(data) {
      this.visible=data.isVisible == "1"?true:false
      this.backBtnUrl = data.link
    }
  }
 
}
</script>
