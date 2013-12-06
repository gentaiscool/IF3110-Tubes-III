package helloJsp.model;

public class ModelInventori {

	int id_inventori;
	int id_kategori;
	String nama_inventori;
	int jumlah;
	String gambar;
	String description;
	int harga;
	
	public ModelInventori() {
		// TODO Auto-generated constructor stub
	}

	public int getId_inventori() {
		return id_inventori;
	}

	public int getId_kategori() {
		return id_kategori;
	}

	public String getNama_inventori() {
		return nama_inventori;
	}

	public int getJumlah() {
		return jumlah;
	}

	public String getGambar() {
		return gambar;
	}

	public String getDescription() {
		return description;
	}

	public void setId_inventori(int id_inventori) {
		this.id_inventori = id_inventori;
	}

	public void setId_kategori(int id_kategori) {
		this.id_kategori = id_kategori;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}

	public void setNama_inventori(String nama_inventori) {
		this.nama_inventori = nama_inventori;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
