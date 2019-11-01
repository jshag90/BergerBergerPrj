<template>
  <div>버거킹 메뉴페이지</div>
  <!-- <div>{{ menus }}</div> -->
</template>

<script>
export default {
   mounted() {
      const baseURI = 'http://192.168.0.16:8080'; 
       this.$http.post(`${baseURI}/getBurgerMenus`, { BRAND:"burgerking", CATEGORY:data, PRICE_ORDER:"ASC" })
      .then((result) => {
        this.menus = result.data
        console.log(this.menus)
      })
  },
  data: () =>({
    menus:[]
  })

}
</script>

<style>

</style>