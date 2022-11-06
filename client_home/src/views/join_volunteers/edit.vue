<template>
	<div class="diy_edit page_join_volunteers" id="join_volunteers_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row'>
					<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>用户名:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user_name" :disabled="disabledObj['user_name_isDisabled']" v-model="form['user_name']" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))" >
								<option v-for="o in list_user_user_name" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user_name')">{{ form['user_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','full_name') || $check_field('add','full_name') || $check_field('get','full_name')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>姓名:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_full_name" v-model="form['full_name']" placeholder="请输入姓名" v-if="(form['full_name'] && $check_field('set','full_name')) || (!form['full_name'] && $check_field('add','full_name'))"  :disabled="disabledObj['full_name_isDisabled']"/>
							<span v-else-if="$check_field('get','full_name')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_information') || $check_field('add','contact_information') || $check_field('get','contact_information')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>联系方式:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_information" v-model="form['contact_information']" placeholder="请输入联系方式" v-if="(form['contact_information'] && $check_field('set','contact_information')) || (!form['contact_information'] && $check_field('add','contact_information'))"  :disabled="disabledObj['contact_information_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_information')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','remarks') || $check_field('add','remarks') || $check_field('get','remarks')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>备注:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_remarks" v-model="form['remarks']" placeholder="请输入备注" v-if="(form['remarks'] && $check_field('set','remarks')) || (!form['remarks'] && $check_field('add','remarks'))"  :disabled="disabledObj['remarks_isDisabled']"/>
							<span v-else-if="$check_field('get','remarks')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','application_details_') || $check_field('add','application_details_') || $check_field('get','application_details_')" class="col-12 col-md-6">
						<div class="diy_title">
							<span>申请详情:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_application_details_" v-model="form['application_details_']" v-if="(form['application_details_'] && $check_field('set','application_details_')) || (!form['application_details_'] && $check_field('add','application_details_'))" :disabled="disabledObj['application_details__isDisabled']" />
							<span v-else-if="$check_field('get','application_details_')">{{ form['full_name'] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/join_volunteers/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="col-12 col-md-6" >
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/join_volunteers/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/join_volunteers/get_obj?",
				url_add: "~/api/join_volunteers/add?",
				url_set: "~/api/join_volunteers/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"user_name": 0,
					"full_name": "",
					"contact_information": "",
					"remarks": "",
					"application_details_": "",
					"join_volunteers_id": 0,
				},

				obj: {
					"user_name": 0, // 用户名
					"full_name":'', // 姓名
					"contact_information":'', // 联系方式
					"remarks":'', // 备注
					"application_details_":'', // 申请详情
					"examine_state": "未审核",
					"examine_reply": "",
					"join_volunteers_id": 0,
				},

				// 表单字段
				form: {
					"user_name": 0, // 用户名
					"full_name":'', // 姓名
					"contact_information":'', // 联系方式
					"remarks":'', // 备注
					"application_details_":'', // 申请详情
					"examine_state": "未审核",
					"examine_reply": "",
					"join_volunteers_id": 0,

				},
				disabledObj:{
					"user_name_isDisabled": false,
					"full_name_isDisabled": false,
					"contact_information_isDisabled": false,
					"remarks_isDisabled": false,
					"application_details__isDisabled": false,
				},
				// 用户列表
				list_user_user_name: [],

				// ID字段
				field: "join_volunteers_id",
			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user_name() {
				// if(this.user_group !== "管理员" && this.form["user_name"] === 0) {
				//     this.form["user_name"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=普通用户");
				if(json.result && json.result.list){
					this.list_user_user_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_user_name(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user_name"] = user_id
								_this.disabledObj['user_name' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
									for (var j=0;j<arrForm.length;j++){
										if (arr[i]===arrForm[j]){
											if (arr[i]!=="user_name") {
												_this.form[arrForm[j]] = res.result.obj[arr[i]]
												_this.disabledObj[arrForm[j] + '_isDisabled'] = true
												break;
											}
										}
									}
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/join_volunteers/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (form) {
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user_name();
			this.get_list_user_user_name();
		}
	}
</script>

<style>
</style>
