using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftVenBusiness;

namespace SoftVenTest
{
    [TestClass]
    public class SoftVenBOTest
    {
        private ConductoresBO conductoresBO;

        public SoftVenBOTest()
        {
            conductoresBO = new ConductoresBO();
        }

        [TestMethod]
        public void TestInsertarConductor()
        {
            Console.WriteLine("Insertar Conductor");
            int resultado = conductoresBO.Insertar("PRUEBA", "TEST", "JUAN", "Z123456", 1, 0);
            Assert.IsTrue(resultado > 0);
        }
    }
}
