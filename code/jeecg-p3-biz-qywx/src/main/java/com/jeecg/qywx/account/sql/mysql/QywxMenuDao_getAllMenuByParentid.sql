SELECT * FROM qywx_menu qm 
	where qm.father_id =:fatherId
ORDER BY qm.agent_id,qm.orders