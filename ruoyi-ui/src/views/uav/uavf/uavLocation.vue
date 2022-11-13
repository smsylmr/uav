<template>
  <div>
    <h1>ssss</h1>
    <div id="container" class="map1">
        <baidu-map class="bm-view"  @ready="init"></baidu-map>
</div>
  </div>
</template>

<script>
import { runInThisContext } from 'vm';

export default {
    name:'uavLocation',
    created(){
        //this.initMapLocation()

    },
    methods:{
        initMapLocation(){
            //初始化地图方法
            var map = new BMapGL.Map("container");
      map.centerAndZoom(new BMapGL.Point(115.500528, 37.119744), 15);
      //设置地图事件
      map.enableScrollWheelZoom(); // 允许滚轮缩放
      map.enableKeyboard();
      map.enableDragging();
      map.enableDoubleClickZoom()

    },
    init({BMap, map}) {
    const mapL = map;
    // 初始化地图,设置中心点坐标
    var point = new BMap.Point(119.80250895, 25.48905564);
    mapL.centerAndZoom(point, 14);
    //this.setMarker();
    // 添加鼠标滚动缩放
    mapL.enableScrollWheelZoom();
},
    }

}
</script>

<style scoped>
.map1{
  width:500px;
  height:600px;
  margin: 0 auto;
}
</style>