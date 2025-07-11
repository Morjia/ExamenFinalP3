using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenPersistance.DAO;
using System.ComponentModel;
using System.Data.Common;
using SoftVen.DAOImpl.Util;
using SoftVenModel;

namespace SoftVen.DAOImpl
{
    public class VehiculosDAOImpl : DAOImplBase, VehiculosDAO
    {
        private VehiculosDTO vehiculo;

        public VehiculosDAOImpl() : base("EX1_VEHICULOS")
        {
            this.retornarLlavePrimaria = true;
            this.vehiculo = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("VEHICULO_ID", true, true));
            this.listaColumnas.Add(new Columna("PLACA", false, false));
            this.listaColumnas.Add(new Columna("MARCA", false, false));
            this.listaColumnas.Add(new Columna("MODELO", false, false));
            this.listaColumnas.Add(new Columna("ANHO", false, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@PLACA", this.vehiculo.Placa);
            this.AgregarParametro("@MARCA", this.vehiculo.Marca);
            this.AgregarParametro("@MODELO", this.vehiculo.Modelo);
            this.AgregarParametro("@ANHO", this.vehiculo.Anho);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.AgregarParametro("@PLACA", this.vehiculo.Placa);
            this.AgregarParametro("@MARCA", this.vehiculo.Marca);
            this.AgregarParametro("@MODELO", this.vehiculo.Modelo);
            this.AgregarParametro("@ANHO", this.vehiculo.Anho);
            this.AgregarParametro("@VEHICULO_ID", this.vehiculo.VehiculoId);
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.AgregarParametro("@VEHICULO_ID", this.vehiculo.VehiculoId);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.AgregarParametro("@VEHICULO_ID", this.vehiculo.VehiculoId);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.vehiculo = new VehiculosDTO();
            this.vehiculo.VehiculoId = lector.GetInt32(0);
            this.vehiculo.Placa = lector.GetString(1);
            this.vehiculo.Marca = lector.GetString(2);
            this.vehiculo.Modelo = lector.GetString(3);
            this.vehiculo.Anho = lector.GetInt32(4);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.vehiculo = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.vehiculo);
        }

        public int Insertar(VehiculosDTO vehiculo)
        {
            this.vehiculo = vehiculo;
            return base.Insertar();
        }

        public int Modificar(VehiculosDTO vehiculo)
        {
            this.vehiculo = vehiculo;
            return base.Modificar();
        }

        public int Eliminar(VehiculosDTO vehiculo)
        {
            this.vehiculo = vehiculo;
            return base.Eliminar();
        }

        public VehiculosDTO ObtenerPorId(int vehiculoId)
        {
            this.vehiculo = new VehiculosDTO();
            this.vehiculo.VehiculoId = vehiculoId;
            base.ObtenerPorId();
            return this.vehiculo;
        }

        public new BindingList<VehiculosDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<VehiculosDTO> retorno = new BindingList<VehiculosDTO>();
            foreach (VehiculosDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }
}

