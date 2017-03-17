SELECT * FROM qywx_menu qm 
	where qm.agent_id =:agentid
	and   (qm.father_id is null or qm.father_id ='')
ORDER BY qm.agent_id,qm.orders