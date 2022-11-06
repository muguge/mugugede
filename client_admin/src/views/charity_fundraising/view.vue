<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','user_name') || $check_field('add','user_name') || $check_field('set','user_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户名" prop="user_name">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user_name(form['user_name']) }}
							<!--<el-input id="business_name" v-model="form['user_name']" placeholder="请输入用户名"-->
							<!--v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','user_name')) || (!form['charity_fundraising_id'] && $check_field('add','user_name'))" :disabled="disabledObj['user_name_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user_name')">{{form['user_name']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','user_name')) || (!form['charity_fundraising_id'] && $check_field('add','user_name'))" id="user_name" v-model="form['user_name']" :disabled="disabledObj['user_name_isDisabled']">
								<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user_name')" id="user_name" v-model="form['user_name']" :disabled="true">
								<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user_name" v-model="form['user_name']" :disabled="disabledObj['user_name_isDisabled']">
							<el-option v-for="o in list_user_user_name" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','full_name')) || (!form['charity_fundraising_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','fundraising_type') || $check_field('add','fundraising_type') || $check_field('set','fundraising_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="募捐类型" prop="fundraising_type">
					<el-input id="fundraising_type" v-model="form['fundraising_type']" placeholder="请输入募捐类型"
							  v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','fundraising_type')) || (!form['charity_fundraising_id'] && $check_field('add','fundraising_type'))" :disabled="disabledObj['fundraising_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','fundraising_type')">{{form['fundraising_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_name') || $check_field('add','item_name') || $check_field('set','item_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品名称" prop="item_name">
					<el-input id="item_name" v-model="form['item_name']" placeholder="请输入物品名称"
							  v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','item_name')) || (!form['charity_fundraising_id'] && $check_field('add','item_name'))" :disabled="disabledObj['item_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_name')">{{form['item_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','amount_of_contributions') || $check_field('add','amount_of_contributions') || $check_field('set','amount_of_contributions')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="捐款数额" prop="amount_of_contributions">
					<el-input id="amount_of_contributions" v-model="form['amount_of_contributions']" placeholder="请输入捐款数额"
							  v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','amount_of_contributions')) || (!form['charity_fundraising_id'] && $check_field('add','amount_of_contributions'))" :disabled="disabledObj['amount_of_contributions_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','amount_of_contributions')">{{form['amount_of_contributions']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remarks') || $check_field('add','remarks') || $check_field('set','remarks')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注" prop="remarks">
					<el-input id="remarks" v-model="form['remarks']" placeholder="请输入备注"
							  v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','remarks')) || (!form['charity_fundraising_id'] && $check_field('add','remarks'))" :disabled="disabledObj['remarks_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remarks')">{{form['remarks']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_information') || $check_field('add','contact_information') || $check_field('set','contact_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系方式" prop="contact_information">
					<el-input id="contact_information" v-model="form['contact_information']" placeholder="请输入联系方式"
							  v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','contact_information')) || (!form['charity_fundraising_id'] && $check_field('add','contact_information'))" :disabled="disabledObj['contact_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_information')">{{form['contact_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','item_details') || $check_field('add','item_details') || $check_field('set','item_details')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="物品详情" prop="item_details">
					<el-input type="textarea" id="item_details" v-model="form['item_details']" placeholder="请输入物品详情"
						v-if="user_group === '管理员' || (form['charity_fundraising_id'] && $check_field('set','item_details')) || (!form['charity_fundraising_id'] && $check_field('add','item_details'))" :disabled="disabledObj['item_details_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','item_details')">{{form['item_details']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核状态" prop="examine_state">
					<el-select id="examine_state" v-model="form['examine_state']"
						v-if="user_group === '管理员' || (form['examine_state'] && $check_examine()) || (!form['examine_state'] && $check_examine())">
						<el-option key="未审核" label="未审核" value="未审核"></el-option>
						<el-option key="已通过" label="已通过" value="已通过"></el-option>
						<el-option key="未通过" label="未通过" value="未通过"></el-option>
					</el-select>
					<div v-else-if="$check_field('get','examine_state')">{{form["examine_state"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item label="审核回复" prop="examine_reply">
					<el-input id="examine_reply" v-model="form['examine_reply']" placeholder="请输入审核回复"
						v-if="user_group === '管理员' || (form['examine_reply'] && $check_examine()) || (!form['examine_reply'] && $check_examine())"></el-input>
					<div v-else-if="$check_field('get','examine_reply')">{{form["examine_reply"]}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "charity_fundraising_id",
				url_add: "~/api/charity_fundraising/add?",
				url_set: "~/api/charity_fundraising/set?",
				url_get_obj: "~/api/charity_fundraising/get_obj?",
				url_upload: "~/api/charity_fundraising/upload?",

				query: {
					"charity_fundraising_id": 0,
				},

				form: {
					"user_name": 0, // 用户名
					"full_name":'', // 姓名
					"fundraising_type":'', // 募捐类型
					"item_name":'', // 物品名称
					"amount_of_contributions":'', // 捐款数额
					"remarks":'', // 备注
					"contact_information":'', // 联系方式
					"item_details":'', // 物品详情
					"examine_state": "未审核",
					"examine_reply": "",
					"charity_fundraising_id": 0, // ID

				},
				disabledObj:{
					"user_name_isDisabled": false,
					"full_name_isDisabled": false,
					"fundraising_type_isDisabled": false,
					"item_name_isDisabled": false,
					"amount_of_contributions_isDisabled": false,
					"remarks_isDisabled": false,
					"contact_information_isDisabled": false,
					"item_details_isDisabled": false,
				},
				// 用户列表
				list_user_user_name: [],
				// 用户组
				group_user_user_name: "",

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
			/**
			 * 获取普通用户用户组
			 */
			async get_group_user_user_name() {
				this.form["user_name"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_user_name = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user_name(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user_name.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="user_name") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_user_name(id){
				var obj = this.list_user_user_name.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "用户名":
							if(param["user_name"] > 0){
								param["user_name"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/charity_fundraising/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/charity_fundraising/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/charity_fundraising/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/charity_fundraising/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/charity_fundraising/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_user_name();
			this.get_group_user_user_name();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
