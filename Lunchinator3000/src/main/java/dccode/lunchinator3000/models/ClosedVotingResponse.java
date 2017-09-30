package dccode.lunchinator3000.models;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Doug
 *
 */
public class ClosedVotingResponse {

	private ClosedVotingWinner winner;
	private List<ClosedVotingChoice> choices = new ArrayList<ClosedVotingChoice>();
	
	public ClosedVotingResponse() {
		super();
	}
	
	// Getters and Setters
	public ClosedVotingWinner getWinner() {
		return winner;
	}
	public void setWinner(ClosedVotingWinner winner) {
		this.winner = winner;
	}
	public List<ClosedVotingChoice> getChoices() {
		return choices;
	}
	public void setChoices(List<ClosedVotingChoice> choices) {
		this.choices = choices;
	}
}
