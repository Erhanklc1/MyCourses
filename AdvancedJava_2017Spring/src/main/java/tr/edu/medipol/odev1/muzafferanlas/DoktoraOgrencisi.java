package tr.edu.medipol.odev1.muzafferanlas;

public class DoktoraOgrencisi extends Ogrenci{
	
	public DoktoraOgrencisi(String adSoyad, String bolum) {
		super(adSoyad, bolum);
	}

	@Override
	public String ogrenciBilgileriAl() {
		return ogrenciTipiniAl() + " " +  adSoyad;
	}
	
	@Override
	public String ogrenciTipiniAl() {
		return "Doktora Ogrencisi";
	}
	

}
