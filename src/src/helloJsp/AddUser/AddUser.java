/**
 * AddUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package helloJsp.AddUser;

public interface AddUser extends java.rmi.Remote {
    public java.lang.String createUser(java.lang.String nama_pengguna, java.lang.String username, java.lang.String password, java.lang.String email, java.lang.String nomor_hp, java.lang.String alamat, java.lang.String provinsi, java.lang.String kota_kabupaten, java.lang.String kode_pos) throws java.rmi.RemoteException;
}
