package helloJsp.AddUser;

public class AddUserProxy implements helloJsp.AddUser.AddUser {
  private String _endpoint = null;
  private helloJsp.AddUser.AddUser addUser = null;
  
  public AddUserProxy() {
    _initAddUserProxy();
  }
  
  public AddUserProxy(String endpoint) {
    _endpoint = endpoint;
    _initAddUserProxy();
  }
  
  private void _initAddUserProxy() {
    try {
      addUser = (new helloJsp.AddUser.AddUserServiceLocator()).getAddUser();
      if (addUser != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)addUser)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)addUser)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (addUser != null)
      ((javax.xml.rpc.Stub)addUser)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public helloJsp.AddUser.AddUser getAddUser() {
    if (addUser == null)
      _initAddUserProxy();
    return addUser;
  }
  
  public java.lang.String createUser(java.lang.String nama_pengguna, java.lang.String username, java.lang.String password, java.lang.String email, java.lang.String nomor_hp, java.lang.String alamat, java.lang.String provinsi, java.lang.String kota_kabupaten, java.lang.String kode_pos) throws java.rmi.RemoteException{
    if (addUser == null)
      _initAddUserProxy();
    return addUser.createUser(nama_pengguna, username, password, email, nomor_hp, alamat, provinsi, kota_kabupaten, kode_pos);
  }
  
  
}