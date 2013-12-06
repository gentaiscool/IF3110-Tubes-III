package helloJsp.controller;

public class UserBean {
	private String name;
	private String username;
	private String password;
	private String email;
	private String nohp;
	private String alamat;
	private String provinsi;
	private String kota;
	private String kodepos;
	private String trans;
	
	public UserBean(){
		name = null;
		username = null;
		password = null;
		email = null;
		nohp = null;
		alamat = null;
		provinsi = null;
		kota = null;
		kodepos = null;
		trans = null;
	}
	
	public UserBean(String _name,String _username,String _password,String _email,String _nohp,String _alamat,String _provinsi,String _kota,String _kodepos,String _trans){
		name = _name;
		username = _username;
		password = _password;
		email = _email;
		nohp = _nohp;
		alamat = _alamat;
		provinsi = _provinsi;
		kota = _kota;
		kodepos = _kodepos;
		trans = _trans;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNohp() {
		return nohp;
	}

	public void setNohp(String nohp) {
		this.nohp = nohp;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getProvinsi() {
		return provinsi;
	}

	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getKodepos() {
		return kodepos;
	}

	public void setKodepos(String kodepos) {
		this.kodepos = kodepos;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}
	
}
