using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVen.DAOImpl;
using SoftVen.DAOImpl.Util;
using SoftVenModel;
using SoftVenPersistance.DAO;

namespace SoftVenPersistance.DAOImpl
{
    public class TiposLicenciasDAOImpl : DAOImplBase, TiposLicenciasDAO
    {
        private TiposLicenciasDTO tipoLicencia;

        public TiposLicenciasDAOImpl() : base("EX1_TIPOS_LICENCIAS")
        {
            this.retornarLlavePrimaria = true;
            this.tipoLicencia = null;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("TIPO_LICENCIA_ID", true, true));
            this.listaColumnas.Add(new Columna("NOMBRE", false, false));
            this.listaColumnas.Add(new Columna("DESCRIPCION", false, false));
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            this.AgregarParametro("@NOMBRE", this.tipoLicencia.Nombre);
            this.AgregarParametro("@DESCRIPCION", this.tipoLicencia.Descripcion);
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            this.AgregarParametro("@NOMBRE", this.tipoLicencia.Nombre);
            this.AgregarParametro("@DESCRIPCION", this.tipoLicencia.Descripcion);
            this.AgregarParametro("@TIPO_LICENCIA_ID", this.tipoLicencia.TipoLicenciaId);
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            this.AgregarParametro("@TIPO_LICENCIA_ID", this.tipoLicencia.TipoLicenciaId);
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            this.AgregarParametro("@TIPO_LICENCIA_ID", this.tipoLicencia.TipoLicenciaId);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.tipoLicencia = new TiposLicenciasDTO();
            this.tipoLicencia.TipoLicenciaId = lector.GetInt32(0);
            this.tipoLicencia.Nombre = lector.GetString(1);
            this.tipoLicencia.Descripcion = lector.GetString(2);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.tipoLicencia = null;
        }

        protected override void AgregarObjetoALaLista(BindingList<object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.tipoLicencia);
        }

        public int Insertar(TiposLicenciasDTO tipoLicencia)
        {
            this.tipoLicencia = tipoLicencia;
            return base.Insertar();
        }

        public int Modificar(TiposLicenciasDTO tipoLicencia)
        {
            this.tipoLicencia = tipoLicencia;
            return base.Modificar();
        }

        public int Eliminar(TiposLicenciasDTO tipoLicencia)
        {
            this.tipoLicencia = tipoLicencia;
            return base.Eliminar();
        }

        public TiposLicenciasDTO ObtenerPorId(int tipoLicenciaId)
        {
            this.tipoLicencia = new TiposLicenciasDTO();
            this.tipoLicencia.TipoLicenciaId = tipoLicenciaId;
            base.ObtenerPorId();
            return this.tipoLicencia;
        }

        public new BindingList<TiposLicenciasDTO> ListarTodos()
        {
            BindingList<object> lista = base.ListarTodos();
            BindingList<TiposLicenciasDTO> retorno = new BindingList<TiposLicenciasDTO>();
            foreach (TiposLicenciasDTO obj in lista)
            {
                retorno.Add(obj);
            }
            return retorno;
        }
    }
}
