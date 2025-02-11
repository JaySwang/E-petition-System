package service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.StaleObjectStateException;

import dao.ProposalDAO;


import model.BaseEntityBean;
import model.Proposal;


public class ProposalServiceImpl   implements IProposalService {

	ProposalDAO pdao;
	
	
	public ProposalDAO getPdao() {
		return pdao;
	}

	public void setPdao(ProposalDAO pdao) {
		this.pdao = pdao;
	}


	public void save(Proposal baseBean) {
		pdao.create(baseBean);		
		
	}

	public void delete(Proposal baseBean) {
		// TODO Auto-generated method stub
		
	}

	public List<Proposal> getProposals() {

	
		 
			List proposals = new ArrayList();
			proposals =	pdao.getProposals();

		return proposals;
	}


	@Override
	public Proposal getProposalById(int id) {
		return (Proposal) pdao.find(id);
	}

	@Override
	public void voteAgree(Proposal p, boolean agree) {
		if(agree){
			p.agree();
		}else{
			p.disagree();
		}
		pdao.update(p);

	}

	@Override
	public void update(Proposal p) {
		pdao.update(p);		
	}

	@Override
	public List<Proposal> getGeneralProposals() {
		List proposals = new ArrayList();
		proposals =	pdao.getProposalsByType("General Proposal");
		return proposals;
	}

	@Override
	public List<Proposal> getNotGeneralProposals() {
		List<Proposal> proposalsTemp = new ArrayList();
		proposalsTemp =	pdao.getProposals();
		List proposals = new ArrayList();
        for(Proposal p:proposalsTemp){
        	if(!(p.getType().equalsIgnoreCase("General Proposal"))){
        		proposals.add(p);
        	}
        }
		return proposals;
	}

	
}
