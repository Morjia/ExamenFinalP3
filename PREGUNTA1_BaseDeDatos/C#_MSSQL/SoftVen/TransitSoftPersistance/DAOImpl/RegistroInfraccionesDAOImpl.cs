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
    public class RegistroInfraccionesDAOImpl : DAOImplBase, RegistroInfraccionesDAO
    {
        private RegistroInfraccionesDTO registro;

        public RegistroInfraccionesDAOImpl() : base("EX1_REGISTRO_INFRACCIONES")
        {
            this.retornarLlavePrimaria = false;
            this.registro = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("FECHA", true, false));
            this.listaColumnas.Add(new Columna("VEHICULO_ID", true, false));
            this.listaColumnas.Add(new Columna("CONDUCTOR_ID", true, false));
            this.listaColumnas.Add(new Columna("INFRACCION_ID", true, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@FECHA", this.registro.Fecha);
            this.AgregarParametro("@VEHICULO_ID", this.registro.VehiculoId);
            this.AgregarParametro("@CONDUCTOR_ID", this.registro.ConductorId);
            this.AgregarParametro("@INFRACCION_ID", this.registro.InfraccionId);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.IncluirValorDeParametrosParaInsercion();
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.IncluirValorDeParametrosParaInsercion();
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.IncluirValorDeParametrosParaInsercion();
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.registro = new RegistroInfraccionesDTO();
            this.registro.Fecha = lector.GetDateTime(0);
            this.registro.VehiculoId = lector.GetInt32(1);
            this.registro.ConductorId = lector.GetInt32(2);
            this.registro.InfraccionId = lector.GetInt32(3);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.registro = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.registro);
        }

        public int Insertar(RegistroInfraccionesDTO registro)
        {
            this.registro = registro;
            return base.Insertar();
        }

        public int Modificar(RegistroInfraccionesDTO registro)
        {
            this.registro = registro;
            return base.Modificar();
        }

        public int Eliminar(RegistroInfraccionesDTO registro)
        {
            this.registro = registro;
            return base.Eliminar();
        }

        public RegistroInfraccionesDTO ObtenerPorId(DateTime fecha, int vehiculoId, int conductorId, int infraccionId)
        {
            this.registro = new RegistroInfraccionesDTO();
            this.registro.Fecha = fecha;
            this.registro.VehiculoId = vehiculoId;
            this.registro.ConductorId = conductorId;
            this.registro.InfraccionId = infraccionId;
            base.ObtenerPorId();
            return this.registro;
        }

        public new BindingList<RegistroInfraccionesDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<RegistroInfraccionesDTO> retorno = new BindingList<RegistroInfraccionesDTO>();
            foreach (RegistroInfraccionesDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }

}
