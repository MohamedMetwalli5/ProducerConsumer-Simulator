<template>
  <div>
    <h1>Number of products : {{ NumberOfProducts }}</h1>
    <div class="ButtonsGroup">
      <button class="ButtonItem" @click="AddMachine(), MachineCounter++">
        Add machine 🏭
      </button>
      <button class="ButtonItem" @click="AddQueue(), QueueCounter++">
        Add queue 📦
      </button>
      <button class="ButtonItem" @click="AddProduct()">Add product ➕</button>
      <button class="ButtonItem" @click="StartSimulation()">
        Start simulation ▶️
      </button>
    </div>

    <div class="Element">
      <button id="MachineElement" style="display: none"></button>

      <button id="QueueElement" style="display: none"></button>
    </div>
  </div>
</template>
<script>
export default {
  name: "Shapes",
  data: function () {
    return {
      MachineCounter: 0,
      QueueCounter: 0,
      pickup: false,
      StartSimulationFlag: false,
      NumberOfProducts: 0,
    };
  },
  methods: {
    drag(index) {
      let doc = document.getElementById(index);
      doc.style.position = "absolute";
      this.pickup = true;
    },
    dragging(index) {
      let doc = document.getElementById(index);
      if (this.pickup) {
        doc.style.left = event.clientX - 10 + "px";
        doc.style.top = event.clientY - 20 + "px";
      }
    },
    drop() {
      this.pickup = false;
    },
    AddMachine() {
      if (!this.StartSimulationFlag) {
        var btn = document.createElement("BUTTON");
        btn.style.width = "70px";
        btn.style.height = "50px";
        btn.style.background = "rgb(20, 218, 63)";
        btn.style.border = "solid";
        btn.style.borderRadius = "30px";
        btn.id = "MachineElementnew";
        btn.textContent = "M" + this.MachineCounter;
        btn.onmousedown = console.log("Fdfdfdf"); //this.drag("MachineElement");
        btn.onmousemove = this.dragging("MachineElement");
        btn.onmouseup = this.drop();
        document.body.appendChild(btn);
        document.body.appendChild(btn);
      }
    },
    AddQueue() {
      if (!this.StartSimulationFlag) {
        var btn = document.createElement("BUTTON");
        btn.style.width = "70px";
        btn.style.height = "50px";
        btn.style.background = "yellow";
        btn.style.border = "solid";
        btn.style.borderRadius = "30px";
        btn.id = "QueueElement";
        btn.textContent = "Q" + this.QueueCounter;
        btn.onmousedown = console.log("ytyryt"); //this.drag("QueueElement");
        btn.onmousemove = this.dragging("QueueElement");
        btn.onmouseup = this.drop();
        document.body.appendChild(btn);
      }
    },
    AddProduct() {
      this.NumberOfProducts++;
    },
    StartSimulation() {
      this.StartSimulationFlag = true;
    },
  },
};
</script>
<style  scoped>
.ButtonsGroup {
  border-radius: 50px;
  margin-top: 1px;
  margin-left: -15px;
  margin-bottom: 30px;
  height: 200px;
  width: 20%;
}
.ButtonItem {
  margin: auto;
  margin-right: 10px;
  width: 60%;
  height: 25%;
  background: rgb(57, 153, 231);
  border-radius: 50px;
}
.ButtonItem:hover {
  background: rgb(22, 116, 194);
}
#MachineElement {
  border: solid;
  border-radius: 30px;
  background: rgb(20, 218, 63);
  height: 50px;
  width: 70px;
}
#QueueElement {
  border: solid;
  border-radius: 30px;
  background: yellow;
  height: 50px;
  width: 70px;
}
</style>