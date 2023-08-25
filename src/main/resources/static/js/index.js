Vue.component('player-card', {
    props: ['player'],
    template: `<div class="card">
                    <div class="card-body">
                        <h6 class="card-title">
                            {{ player.name }}
                        </h6>
                        <p class="card-text">
                            <div>
                                {{ player.description }}
                            </div>
                        </p>
                        <h6>{{ testValue }}</h6>
                    </div>
                </div>`
});

var app = new Vue({
    el: '#contents',
    data: {
        players: [
            {id: "1", name: "Lionel Messi", description: "Argentina's superstar"},
            {id: "2", name: "Christiano Ronaldo", description: "Portugal top-ranked player"}
        ],
        helloWorldTest:'',
    },
    mounted(){
        console.log('fetch data');
        axios.get('hello').then(response  => this.helloWorldTest = response.data);
    },
});
