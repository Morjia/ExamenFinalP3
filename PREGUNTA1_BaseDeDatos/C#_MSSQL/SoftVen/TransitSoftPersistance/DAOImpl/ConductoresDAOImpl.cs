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
    public class ConductoresDAOImpl : DAOImplBase, ConductoresDAO
    {
        private ConductoresDTO conductor;

        public ConductoresDAOImpl() : base("EX1_CONDUCTORES")
        {
            this.retornarLlavePrimaria = true;
            this.conductor = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("CONDUCTOR_ID", true, true));
            this.listaColumnas.Add(new Columna("PATERNO", false, false));
            this.listaColumnas.Add(new Columna("MATERNO", false, false));
            this.listaColumnas.Add(new Columna("NOMBRES", false, false));
            this.listaColumnas.Add(new Columna("NUM_LICENCIA", false, false));
            this.listaColumnas.Add(new Columna("TIPO_LICENCIA_ID", false, false));
            this.listaColumnas.Add(new Columna("PUNTOS_ACUMULADOS", false, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@PATERNO", this.conductor.Paterno);
            this.AgregarParametro("@MATERNO", this.conductor.Materno);
            this.AgregarParametro("@NOMBRES", this.conductor.Nombres);
            this.AgregarParametro("@NUM_LICENCIA", this.conductor.NumLicencia);
            this.AgregarParametro("@TIPO_LICENCIA_ID", this.conductor.TipoLicenciaId);
            this.AgregarParametro("@PUNTOS_ACUMULADOS", this.conductor.PuntosAcumulados);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.IncluirValorDeParametrosParaInsercion();
            this.AgregarParametro("@CONDUCTOR_ID", this.conductor.ConductorId);
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.AgregarParametro("@CONDUCTOR_ID", this.conductor.ConductorId);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.AgregarParametro("@CONDUCTOR_ID", this.conductor.ConductorId);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.conductor = new ConductoresDTO();
            this.conductor.ConductorId = lector.GetInt32(0);
            this.conductor.Paterno = lector.IsDBNull(1) ? null : lector.GetString(1);
            this.conductor.Materno = lector.IsDBNull(2) ? null : lector.GetString(2);
            this.conductor.Nombres = lector.IsDBNull(3) ? null : lector.GetString(3);
            this.conductor.NumLicencia = lector.IsDBNull(4) ? null : lector.GetString(4);
            this.conductor.TipoLicenciaId = lector.IsDBNull(5) ? 0 : lector.GetInt32(5);
            this.conductor.PuntosAcumulados = lector.IsDBNull(6) ? 0 : lector.GetInt32(6);
        }


        protected override void LimpiarObjetoDelResultSet()
        {
            this.conductor = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.conductor);
        }

        public int Insertar(ConductoresDTO conductor)
        {
            this.conductor = conductor;
            return base.Insertar();
        }

        public int Modificar(ConductoresDTO conductor)
        {
            this.conductor = conductor;
            return base.Modificar();
        }

        public int Eliminar(ConductoresDTO conductor)
        {
            this.conductor = conductor;
            return base.Eliminar();
        }

        public ConductoresDTO ObtenerPorId(int conductorId)
        {
            this.conductor = new ConductoresDTO();
            this.conductor.ConductorId = conductorId;
            base.ObtenerPorId();
            return this.conductor;
        }

        public new BindingList<ConductoresDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<ConductoresDTO> retorno = new BindingList<ConductoresDTO>();
            foreach (ConductoresDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }
}
