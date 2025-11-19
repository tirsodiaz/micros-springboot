package model;

public class NombrePais {
    private String common;
    private String official;

    public NombrePais(String common, String official) {
		super();
		this.common = common;
		this.official = official;
	}
	public NombrePais() {
		super();
		// TODO Auto-generated constructor stub
	}
	// Getters y setters
    public String getCommon() { return common; }
    public void setCommon(String common) { this.common = common; }

    public String getOfficial() { return official; }
    public void setOfficial(String official) { this.official = official; }
}