using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVen.DAOImpl.Util;
using SoftVen.DAOImpl;
using SoftVenModel;
using SoftVenPersistance.DAO;

namespace SoftVenPersistance.DAOImpl
{
    public class VehiculosPorConductorDAOImpl : DAOImplBase, VehiculosPorConductorDAO
    {
        private VehiculosPorConductorDTO vehiculoPorConductor;

        public VehiculosPorConductorDAOImpl() : base("EX1_VEHICULOS_POR_CONDUCTOR")
        {
            this.retornarLlavePrimaria = false;
            this.vehiculoPorConductor = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("VEHICULO_ID", true, false));
            this.listaColumnas.Add(new Columna("CONDUCTOR_ID", true, false));
            this.listaColumnas.Add(new Columna("FECHA_ADQUISICION", false, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@VEHICULO_ID", this.vehiculoPorConductor.VehiculoId);
            this.AgregarParametro("@CONDUCTOR_ID", this.vehiculoPorConductor.ConductorId);
            this.AgregarParametro("@FECHA_ADQUISICION", this.vehiculoPorConductor.FechaAdquisicion);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.IncluirValorDeParametrosParaInsercion();
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.AgregarParametro("@VEHICULO_ID", this.vehiculoPorConductor.VehiculoId);
            this.AgregarParametro("@CONDUCTOR_ID", this.vehiculoPorConductor.ConductorId);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.AgregarParametro("@VEHICULO_ID", this.vehiculoPorConductor.VehiculoId);
            this.AgregarParametro("@CONDUCTOR_ID", this.vehiculoPorConductor.ConductorId);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.vehiculoPorConductor = new VehiculosPorConductorDTO();
            this.vehiculoPorConductor.VehiculoId = lector.GetInt32(0);
            this.vehiculoPorConductor.ConductorId = lector.GetInt32(1);
            this.vehiculoPorConductor.FechaAdquisicion = lector.GetDateTime(2);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.vehiculoPorConductor = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.vehiculoPorConductor);
        }

        public int Insertar(VehiculosPorConductorDTO vehiculoPorConductor)
        {
            this.vehiculoPorConductor = vehiculoPorConductor;
            return base.Insertar();
        }

        public int Modificar(VehiculosPorConductorDTO vehiculoPorConductor)
        {
            this.vehiculoPorConductor = vehiculoPorConductor;
            return base.Modificar();
        }

        public int Eliminar(VehiculosPorConductorDTO vehiculoPorConductor)
        {
            this.vehiculoPorConductor = vehiculoPorConductor;
            return base.Eliminar();
        }

        public VehiculosPorConductorDTO ObtenerPorId(int vehiculoId, int conductorId)
        {
            this.vehiculoPorConductor = new VehiculosPorConductorDTO();
            this.vehiculoPorConductor.VehiculoId = vehiculoId;
            this.vehiculoPorConductor.ConductorId = conductorId;
            base.ObtenerPorId();
            return this.vehiculoPorConductor;
        }

        public new BindingList<VehiculosPorConductorDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<VehiculosPorConductorDTO> retorno = new BindingList<VehiculosPorConductorDTO>();
            foreach (VehiculosPorConductorDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }
}
