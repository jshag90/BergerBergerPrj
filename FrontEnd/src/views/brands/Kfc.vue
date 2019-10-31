<template>
 <div>
   kfc
     <div>{{category}}</div>
  </div>
</template>

<script>
export default {
  mounted() {
     const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/getBurgerCategory`, { BRAND:"kfc" })
      .then((result) => {
        this.category = result.data
      })
  },
  data: () =>({
    category:''
  })
  
}
</script>

<style>

</style>