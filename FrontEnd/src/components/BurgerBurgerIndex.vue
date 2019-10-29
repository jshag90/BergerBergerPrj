<template>
  <v-card
    class="mx-auto overflow-hidden"
    max-width="500"
    max-height="1000"
  >
    <!-- <v-system-bar
      color="indigo darken-2"
      dark
    >
      <v-spacer></v-spacer>

      <v-icon>mdi-window-minimize</v-icon>

      <v-icon>mdi-window-maximize</v-icon>

      <v-icon>mdi-close</v-icon>
    </v-system-bar> -->

    <v-toolbar
      color="indigo"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" ></v-app-bar-nav-icon>

      <v-toolbar-title>버거버거</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </v-toolbar>

     <v-navigation-drawer
      v-model="drawer"
      absolute
      bottom
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item to="/home">
            <v-list-item-title>버거킹</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>맥도날드</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-title>KFC</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-container fluid>
      <v-row dense>
        <v-col
          v-for="card in cards"
          :key="card.title"
          :cols="card.flex"
        >
          <v-card>
            <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="170px"
            >
              <v-card-title v-text="card.title"></v-card-title>
            </v-img>

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
      { title: '버거킹', src: 'https://d1cua0vf0mkpiy.cloudfront.net/images/menu/normal/bf072bee-bb2f-49a0-b80a-a737daeab66e.png', flex: 12 },
      { title: '맥도날드', src: 'https://d1cua0vf0mkpiy.cloudfront.net/images/menu/normal/6c1476a9-dd99-48ed-982b-4ca391a3ee20.png', flex: 12 },
      { title: '케이에프씨', src: 'https://d1cua0vf0mkpiy.cloudfront.net/images/menu/normal/9e61f1bb-3683-4840-a126-fbf8ab62e703.png', flex: 12 }
    ]
  }),
  watch: {
    group () {
      this.drawer = false
    }
  }
}
</script>
