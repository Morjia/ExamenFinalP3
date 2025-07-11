using System;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using SoftVenBusiness;
using SoftVenModel;

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
            int resultado = conductoresBO.Insertar("TEST", "PRUEBA", "JUAN", "X123456", 1, 10);
            Assert.IsTrue(resultado > 0);
        }

        [TestMethod]
        public void TestObtenerPorId()
        {
            var conductor = conductoresBO.ObtenerPorId(4);
            Assert.IsNotNull(conductor);
        }

        [TestMethod]
        public void TestModificarConductor()
        {
            var conductor = conductoresBO.ObtenerPorId(4);
            conductor.Paterno = "ACTUALIZADO";
            conductoresBO.Modificar(conductor);

            var conductorActualizado = conductoresBO.ObtenerPorId(4);
            Assert.AreEqual("ACTUALIZADO", conductorActualizado.Paterno);
        }

        [TestMethod]
        public void TestListarTodos()
        {
            var lista = conductoresBO.ListarTodos();
            Assert.IsTrue(lista.Count > 0);
        }

        [TestMethod]
        public void TestEliminarConductor()
        {
            int idNuevo = conductoresBO.Insertar("A", "B", "C", "Y123456", 1, 0);
            conductoresBO.Eliminar(idNuevo);

        }
    }
}
