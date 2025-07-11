using System;
using System.Data.Common;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftVen.Db;

namespace SoftInvTest
{
    [TestClass]
    public class DBManagerTest
    {
        [TestMethod]
        public void TestCifradoYDescifrado()
        {
            string original = "Programacion3_2025.";
            string cifrado = DBManager.Cifrar(original);
            string descifrado = DBManager.Descifrar(cifrado);

            Console.WriteLine($"Original: {original}");
            Console.WriteLine($"Cifrado: {cifrado}");
            Console.WriteLine($"Descifrado: {descifrado}");

            Assert.AreEqual(original, descifrado);
        }

        [TestMethod]
        public void TestGetInstance()
        {
            Console.WriteLine("GetInstance");
            DBManager dBManager1 = DBManager.Instance;
            DBManager dBManager2 = DBManager.Instance;
            Assert.IsNotNull(dBManager1);
            Assert.IsNotNull(dBManager2);
            Assert.AreEqual(dBManager1, dBManager2);
        }

        [TestMethod]
        public void TestGetConnection()
        {
            Console.WriteLine("GetConnection");
            DbConnection conexion = DBManager.Instance.Connection;
            Assert.IsNotNull(conexion);
            conexion.Open();
        }
    }
}
