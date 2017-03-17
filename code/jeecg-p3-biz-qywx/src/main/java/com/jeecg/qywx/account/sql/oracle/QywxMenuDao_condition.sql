		<#if qywxMenu.agentId ?exists && qywxMenu.agentId ?length gt 0>
		    /* 应用主键ID */
			and qm.agent_id like '%'||:qywxMenu.agentId||'%' 
		</#if>
		<#if qywxMenu.menuName ?exists && qywxMenu.menuName ?length gt 0>
		    /* 菜单标题 */
			and qm.menu_name like '%'||:qywxMenu.menuName||'%' 
		</#if>
		<#if qywxMenu.menuType ?exists && qywxMenu.menuType ?length gt 0>
		    /* 菜单类型 */
			and qm.menu_type like '%'||:qywxMenu.menuType||'%' 
		</#if>
		<#if qywxMenu.menuKey ?exists && qywxMenu.menuKey ?length gt 0>
		    /* 菜单KEY */
			and qm.menu_key like '%'||:qywxMenu.menuKey||'%' 
		</#if>
		<#if qywxMenu.orders ?exists && qywxMenu.orders ?length gt 0>
		    /* 菜单位置 */
			and qm.orders like '%'||:qywxMenu.orders||'%' 
		</#if>
		<#if qywxMenu.msgType ?exists && qywxMenu.msgType ?length gt 0>
		    /* 响应消息类型 */
			and qm.msg_type like '%'||:qywxMenu.msgType||'%' 
		</#if>
		<#if qywxMenu.templateId ?exists && qywxMenu.templateId ?length gt 0>
		    /* 关联素材ID */
			and qm.template_id like '%'||:qywxMenu.templateId||'%' 
		</#if>
		<#if qywxMenu.url ?exists && qywxMenu.url ?length gt 0>
		    /* 网页链接 */
			and qm.url like '%'||:qywxMenu.url||'%' 
		</#if>
		<#if qywxMenu.fatherId ?exists && qywxMenu.fatherId ?length gt 0>
		    /* 父ID */
			and qm.father_id like '%'||:qywxMenu.fatherId||'%' 
		</#if>
		<#if qywxMenu.createName ?exists && qywxMenu.createName ?length gt 0>
		    /* 创建人名称 */
			and qm.create_name like '%'||:qywxMenu.createName||'%' 
		</#if>
		<#if qywxMenu.createBy ?exists && qywxMenu.createBy ?length gt 0>
		    /* 创建人登录名称 */
			and qm.create_by like '%'||:qywxMenu.createBy||'%' 
		</#if>
	    <#if qywxMenu.createDate ?exists>
		    /* 创建日期 */
			and qm.create_date = :qywxMenu.createDate
		</#if>
		<#if qywxMenu.updateName ?exists && qywxMenu.updateName ?length gt 0>
		    /* 更新人名称 */
			and qm.update_name like '%'||:qywxMenu.updateName||'%' 
		</#if>
		<#if qywxMenu.updateBy ?exists && qywxMenu.updateBy ?length gt 0>
		    /* 更新人登录名称 */
			and qm.update_by like '%'||:qywxMenu.updateBy||'%' 
		</#if>
	    <#if qywxMenu.updateDate ?exists>
		    /* 更新日期 */
			and qm.update_date = :qywxMenu.updateDate
		</#if>
