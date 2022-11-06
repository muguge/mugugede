<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="救助资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="流浪动物社区分享"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_name"
				title="普通用户用户名"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_number"
				title="普通用户用户编号"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_full_name"
				title="普通用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/volunteer_users/list', 'get')"
				:list="result_volunteer_users_volunteer_user_name"
				title="志愿者用户志愿者用户名"
				source_table="volunteer_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/volunteer_users/list', 'get')"
				:list="result_volunteer_users_volunteer_number"
				title="志愿者用户志愿者编号"
				source_table="volunteer_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/volunteer_users/list', 'get')"
				:list="result_volunteer_users_full_name"
				title="志愿者用户姓名"
				source_table="volunteer_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/stray_animal_news/list', 'get')"
				:list="result_stray_animal_news_animal_name"
				title="流浪动物消息动物名称"
				source_table="stray_animal_news"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/adoption_information/list', 'get')"
				:list="result_adoption_information_name_of_adopter"
				title="领养信息领养人姓名"
				source_table="adoption_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/adoption_information/list', 'get')"
				:list="result_adoption_information_animal_name"
				title="领养信息动物名称"
				source_table="adoption_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/rescue_station_information/list', 'get')"
				:list="result_rescue_station_information_name_of_rescue_station"
				title="救助站信息救助站名称"
				source_table="rescue_station_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/charity_fundraising/list', 'get')"
				:list="result_charity_fundraising_full_name"
				title="爱心募捐姓名"
				source_table="charity_fundraising"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/charity_fundraising/list', 'get')"
				:list="result_charity_fundraising_fundraising_type"
				title="爱心募捐募捐类型"
				source_table="charity_fundraising"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/join_volunteers/list', 'get')"
				:list="result_join_volunteers_full_name"
				title="加入志愿者姓名"
				source_table="join_volunteers"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_ordinary_users_user_name":[],
			"result_ordinary_users_user_number":[],
			"result_ordinary_users_full_name":[],
			"result_volunteer_users_volunteer_user_name":[],
			"result_volunteer_users_volunteer_number":[],
			"result_volunteer_users_full_name":[],
			"result_stray_animal_news_animal_name":[],
			"result_adoption_information_name_of_adopter":[],
			"result_adoption_information_animal_name":[],
			"result_rescue_station_information_name_of_rescue_station":[],
			"result_charity_fundraising_full_name":[],
			"result_charity_fundraising_fundraising_type":[],
			"result_join_volunteers_full_name":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取流浪动物社区分享
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_ordinary_users_user_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_name = json.result.list;
			result_ordinary_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_users_user_name = result_ordinary_users_user_name
		 	}
		});
	},
	/**
	 * 获取user_number
	 */
	get_ordinary_users_user_number(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_number = json.result.list;
			result_ordinary_users_user_number.map(o => o.title = o['user_number'])
	  			this.result_ordinary_users_user_number = result_ordinary_users_user_number
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_ordinary_users_full_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_full_name = json.result.list;
			result_ordinary_users_full_name.map(o => o.title = o['full_name'])
	  			this.result_ordinary_users_full_name = result_ordinary_users_full_name
		 	}
		});
	},
	/**
	 * 获取volunteer_user_name
	 */
	get_volunteer_users_volunteer_user_name(){
		this.$get("~/api/volunteer_users/get_list?like=0", { page: 1, size: 10, "volunteer_user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_volunteer_users_volunteer_user_name = json.result.list;
			result_volunteer_users_volunteer_user_name.map(o => o.title = o['volunteer_user_name'])
	  			this.result_volunteer_users_volunteer_user_name = result_volunteer_users_volunteer_user_name
		 	}
		});
	},
	/**
	 * 获取volunteer_number
	 */
	get_volunteer_users_volunteer_number(){
		this.$get("~/api/volunteer_users/get_list?like=0", { page: 1, size: 10, "volunteer_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_volunteer_users_volunteer_number = json.result.list;
			result_volunteer_users_volunteer_number.map(o => o.title = o['volunteer_number'])
	  			this.result_volunteer_users_volunteer_number = result_volunteer_users_volunteer_number
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_volunteer_users_full_name(){
		this.$get("~/api/volunteer_users/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_volunteer_users_full_name = json.result.list;
			result_volunteer_users_full_name.map(o => o.title = o['full_name'])
	  			this.result_volunteer_users_full_name = result_volunteer_users_full_name
		 	}
		});
	},
	/**
	 * 获取animal_name
	 */
	get_stray_animal_news_animal_name(){
		this.$get("~/api/stray_animal_news/get_list?like=0", { page: 1, size: 10, "animal_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_stray_animal_news_animal_name = json.result.list;
			result_stray_animal_news_animal_name.map(o => o.title = o['animal_name'])
	  			this.result_stray_animal_news_animal_name = result_stray_animal_news_animal_name
		 	}
		});
	},
	/**
	 * 获取name_of_adopter
	 */
	get_adoption_information_name_of_adopter(){
		this.$get("~/api/adoption_information/get_list?like=0", { page: 1, size: 10, "name_of_adopter": this.query.word }, (json) => {
		  if (json.result) {
			var result_adoption_information_name_of_adopter = json.result.list;
			result_adoption_information_name_of_adopter.map(o => o.title = o['name_of_adopter'])
	  			this.result_adoption_information_name_of_adopter = result_adoption_information_name_of_adopter
		 	}
		});
	},
	/**
	 * 获取animal_name
	 */
	get_adoption_information_animal_name(){
		this.$get("~/api/adoption_information/get_list?like=0", { page: 1, size: 10, "animal_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_adoption_information_animal_name = json.result.list;
			result_adoption_information_animal_name.map(o => o.title = o['animal_name'])
	  			this.result_adoption_information_animal_name = result_adoption_information_animal_name
		 	}
		});
	},
	/**
	 * 获取name_of_rescue_station
	 */
	get_rescue_station_information_name_of_rescue_station(){
		this.$get("~/api/rescue_station_information/get_list?like=0", { page: 1, size: 10, "name_of_rescue_station": this.query.word }, (json) => {
		  if (json.result) {
			var result_rescue_station_information_name_of_rescue_station = json.result.list;
			result_rescue_station_information_name_of_rescue_station.map(o => o.title = o['name_of_rescue_station'])
	  			this.result_rescue_station_information_name_of_rescue_station = result_rescue_station_information_name_of_rescue_station
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_charity_fundraising_full_name(){
		this.$get("~/api/charity_fundraising/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_charity_fundraising_full_name = json.result.list;
			result_charity_fundraising_full_name.map(o => o.title = o['full_name'])
	  			this.result_charity_fundraising_full_name = result_charity_fundraising_full_name
		 	}
		});
	},
	/**
	 * 获取fundraising_type
	 */
	get_charity_fundraising_fundraising_type(){
		this.$get("~/api/charity_fundraising/get_list?like=0", { page: 1, size: 10, "fundraising_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_charity_fundraising_fundraising_type = json.result.list;
			result_charity_fundraising_fundraising_type.map(o => o.title = o['fundraising_type'])
	  			this.result_charity_fundraising_fundraising_type = result_charity_fundraising_fundraising_type
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_join_volunteers_full_name(){
		this.$get("~/api/join_volunteers/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_join_volunteers_full_name = json.result.list;
			result_join_volunteers_full_name.map(o => o.title = o['full_name'])
	  			this.result_join_volunteers_full_name = result_join_volunteers_full_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_ordinary_users_user_name();
		this.get_ordinary_users_user_number();
		this.get_ordinary_users_full_name();
		this.get_volunteer_users_volunteer_user_name();
		this.get_volunteer_users_volunteer_number();
		this.get_volunteer_users_full_name();
		this.get_stray_animal_news_animal_name();
		this.get_adoption_information_name_of_adopter();
		this.get_adoption_information_animal_name();
		this.get_rescue_station_information_name_of_rescue_station();
		this.get_charity_fundraising_full_name();
		this.get_charity_fundraising_fundraising_type();
		this.get_join_volunteers_full_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_ordinary_users_user_name();
	  this.get_ordinary_users_user_number();
	  this.get_ordinary_users_full_name();
	  this.get_volunteer_users_volunteer_user_name();
	  this.get_volunteer_users_volunteer_number();
	  this.get_volunteer_users_full_name();
	  this.get_stray_animal_news_animal_name();
	  this.get_adoption_information_name_of_adopter();
	  this.get_adoption_information_animal_name();
	  this.get_rescue_station_information_name_of_rescue_station();
	  this.get_charity_fundraising_full_name();
	  this.get_charity_fundraising_fundraising_type();
	  this.get_join_volunteers_full_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
