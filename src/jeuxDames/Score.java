package jeuxDames;

public class Score {
	protected String pseudo;
	protected int gagne;
	protected int perdu;
	protected int egalite;
	protected int total;
	
	public Score()
	{
		pseudo = " ";
		gagne = 0;
		perdu = 0;
		egalite = 0;
		total = 0;
	}
	public Score(String p, int g, int pe, int e, int t)
	{
		pseudo = p;
		gagne = g;
		perdu = pe;
		egalite = e;
		total = t;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getGagne() {
		return gagne;
	}
	public void setGagne(int gagne) {
		this.gagne = gagne;
	}
	public int getPerdu() {
		return perdu;
	}
	public void setPerdu(int perdu) {
		this.perdu = perdu;
	}
	public int getEgalite() {
		return egalite;
	}
	public void setEgalite(int egalite) {
		this.egalite = egalite;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
