<template>
	<div class="diy_home diy_list diy_stray_animal_news" id="diy_stray_animal_news_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/stray_animal_news/details?stray_animal_news_id=' + o['stray_animal_news_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/stray_animal_news/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/stray_animal_news/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','animal_name')">动物名称
					</th>
					<th class="diy_title" v-if="$check_field('get','animal_pictures')">动物图片
					</th>
					<th class="diy_title" v-if="$check_field('get','adoption_status')">领养状态
					</th>
					<th class="diy_title" v-if="$check_field('get','animal_sex')">动物性别
					</th>
					<th class="diy_title" v-if="$check_field('get','age_animal')">动物年龄
					</th>
					<th class="diy_title" v-if="$check_field('get','affiliated_rescue_station')">所属救助站
					</th>
					<th class="diy_title" v-if="$check_field('get','species')">物种
					</th>
					<th class="diy_title" v-if="$check_field('get','varieties')">品种
					</th>
					<th class="diy_title" v-if="$check_field('get','vaccination')">是否接种
					</th>
					<th class="diy_title" v-if="$check_field('get','sterilization')">是否绝育
					</th>
					<th class="diy_title" v-if="$check_field('get','is_it_insect_repellent')">是否驱虫
					</th>
					<th class="diy_title" v-if="$check_field('get','adoption_notes')">领养备注
					</th>
					<th class="diy_title" v-if="$check_field('get','detailed_information')">详细信息
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','animal_name')">
						<span>
							{{ o["animal_name"] }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','animal_pictures')">
						<img class="diy_img" :src="o['animal_pictures']" />
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','adoption_status')">
						<span>
							{{ o["adoption_status"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','animal_sex')">
						<span>
							{{ o["animal_sex"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','age_animal')">
						<span>
							{{ o["age_animal"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','affiliated_rescue_station')">
						<span>
							{{ o["affiliated_rescue_station"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','species')">
						<span>
							{{ o["species"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','varieties')">
						<span>
							{{ o["varieties"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','vaccination')">
						<span>
							{{ o["vaccination"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','sterilization')">
						<span>
							{{ o["sterilization"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','is_it_insect_repellent')">
						<span>
							{{ o["is_it_insect_repellent"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','adoption_notes')">
						<span>
							{{ o["adoption_notes"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','detailed_information')">
						<span>
							{{ o["detailed_information"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "动物图片",
							name: "animal_pictures",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "动物名称",
									name: "animal_name",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "领养状态",
									name: "adoption_status",
									type: "下拉",
									is_img_list: "1"
								},
								{
									title: "动物性别",
									name: "animal_sex",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "动物年龄",
									name: "age_animal",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "所属救助站",
									name: "affiliated_rescue_station",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "物种",
									name: "species",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "品种",
									name: "varieties",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "是否接种",
									name: "vaccination",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "是否绝育",
									name: "sterilization",
									type: "下拉",
									is_img_list: "0"
								},
								{
									title: "是否驱虫",
									name: "is_it_insect_repellent",
									type: "下拉",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "领养备注",
									name: "adoption_notes",
									type: "多文本"
								},
								{
									title: "详细信息",
									name: "detailed_information",
									type: "多文本"
								},
						],
			};
		},
		methods: {
		},
		created() {
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

