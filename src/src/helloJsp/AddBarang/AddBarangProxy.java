package helloJsp.AddBarang;

public class AddBarangProxy implements helloJsp.AddBarang.AddBarang {
  private String _endpoint = null;
  private helloJsp.AddBarang.AddBarang addBarang = null;
  
  public AddBarangProxy() {
    _initAddBarangProxy();
  }
  
  public AddBarangProxy(String endpoint) {
    _endpoint = endpoint;
    _initAddBarangProxy();
  }
  
  private void _initAddBarangProxy() {
    try {
      addBarang = (new helloJsp.AddBarang.AddBarangServiceLocator()).getAddBarang();
      if (addBarang != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)addBarang)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)addBarang)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (addBarang != null)
      ((javax.xml.rpc.Stub)addBarang)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public helloJsp.AddBarang.AddBarang getAddBarang() {
    if (addBarang == null)
      _initAddBarangProxy();
    return addBarang;
  }
  
  public java.lang.String createBarang(int id_inventori, int kategori, java.lang.String nama, int jumlah, java.lang.String gambar, java.lang.String description, int harga) throws java.rmi.RemoteException{
    if (addBarang == null)
      _initAddBarangProxy();
    return addBarang.createBarang(id_inventori, kategori, nama, jumlah, gambar, description, harga);
  }
  
  
}