<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" fixed app >
      <v-toolbar color="primary" >
        <v-toolbar-title  class="white--text" > 
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
        <v-btn color="primary" fab small dark href="/">
                  <span class="group pa-2">
                    <v-icon>mdi-home</v-icon>
                  </span>
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>

    <v-content>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data () {
    return {
      drawer: false,
      items: [
        {
          icon: 'mdi-alert-box',
          title: '버거킹(BurgerKing)',
          active: false,
          subItems: [
            {
              title: '버거킹',
              to: '/burgerKing'
            }
          ]
        },
        {
          icon: 'mdi-alert-box',
          title: '맥도날드(Macdonalds)',
          active: false,
          subItems: [
            {
              title: 'card',
              to: '/lectures/card'
            }
          ]
        },
        {
          icon: 'mdi-alert-box',
          title: '케이에프씨(KFC)',
          active: false,
          subItems: [
            {
              title: 'card',
              to: '/lectures/card'
            }
          ]
        }
      ]
    }
  },
  methods: {
    test () {

    }
  }
}
</script>
