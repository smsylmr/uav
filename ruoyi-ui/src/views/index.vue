<template>
  <div class="c1">
    <div class="ca c2">
      <div class="ca c21"></div>
      <div class="ca c22"></div>
      <div class="ca c23">飞行总数据</div>
      <div class="ca c24"></div>
      <div class="ca c25">主板温度(℃)：46</div>
      <div class="ca c26"></div>
      <div class="ca c27">电池剩余(mAh)：531</div>
      <div class="ca c28"></div>
      <div class="ca c29">飞行剩余(km)：8</div>
    </div>
    <div class="ca c3">
      <div class="ca c31"></div>
      <div class="ca c32">机型占比</div>
      <div class="ca c33">按飞行次数</div>

    </div>
  </div>
</template>
<script>

export default {
  components: {  },
  data() {
    return {}
  },
  created(){
    console.log(this.$route.query)
    console.log(this.$route.query.uavId)
  }
}
</script>

<style scoped>
.ca {
  position: absolute;
}
.c1{
  width:100vw;
  height:100vh;
  background-color: #151B25;
  overflow: hidden;
}
.c2{
  width:19.02vw;
  height:15vh;
  top:15.74vh;
  left:1.93vw;
  background-image: url("./../assets/images/uav/u1.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c21{
  width:1.72vw;
  height:3.06vh;
  left:0.61vw;
  top:1.11vh;
  background-image: url("./../assets/images/uav/u11.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;

}
.c22{
  width:5.87vw;
  height:6.67vh;
  top:5.926vh;
  left:1.46vw;
  background-image: url("./../assets/images/uav/u22.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c23{
  width:10.72vw;
  height:3.06vh;
  line-height: 3.06vh;
  top:1.11vh;
  left:2.86vw;
  color:#1EE3F3;
  font-size:0.7rem;
}
.c24{
  width:1.35vw;
  height:2.41vh;
  top:4.26vh;
  left:8.75vw;
  background-image: url("./../assets/images/uav/u24.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c25{
  width:10.35vw;
  height:2.41vh;
  top:4.26vh;
  left:10.31vw;
  line-height:2.41vh;
  color:#FFF;
  font-size:0.7rem;
}
.c26{
  width:1.35vw;
  height:2.41vh;
  top:7.22vh;
  left:8.75vw;
  background-image: url("./../assets/images/uav/u24.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c27{
  width:10.35vw;
  height:2.41vh;
  top:7.22vh;
  left:10.31vw;
  line-height:2.41vh;
  color:#FFF;
  font-size:0.7rem;
}
.c28{
  width:1.35vw;
  height:2.41vh;
  top:10.11vh;
  left:8.75vw;
  background-image: url("./../assets/images/uav/u24.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c29{
  width:10.35vw;
  height:2.41vh;
  top:10.11vh;
  left:10.31vw;
  line-height:2.41vh;
  color:#FFF;
  font-size:0.7rem;
}
.c3{
  width:19.02vw;
  height:36.11vh;
  top:32.96vh;
  left:1.93vw;
  background-image: url("./../assets/images/uav/u3.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c31{
  width:1.72vw;
  height:3.06vh;
  left:0.61vw;
  top:1.11vh;
  background-image: url("./../assets/images/uav/u11.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
}
.c32{
  width:10.72vw;
  height:3.06vh;
  line-height: 3.06vh;
  top:1.11vh;
  left:2.86vw;
  color:#1EE3F3;
  font-size:0.7rem;
}
.c33{
  width:10.72vw;
  height:2.06vh;
  line-height: 2.06vh;
  top:2.20vh;
  left:14.30vw;
  color:#1EE3F3;
  font-size:0.4rem;
}
</style>