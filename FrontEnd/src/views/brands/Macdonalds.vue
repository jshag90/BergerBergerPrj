<template>
 <div>
   맥도날드
     <div>{{category}}</div>
  </div>
</template>

<script>
export default {
  mounted() {
     const baseURI = 'http://localhost:8080';
      this.$http.post(`${baseURI}/getBurgerCategory`, { BRAND:"macdonald" })
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