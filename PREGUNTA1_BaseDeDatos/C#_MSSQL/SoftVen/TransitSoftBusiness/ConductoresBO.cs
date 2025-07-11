using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;
using SoftVenPersistance.DAO;
using SoftVenPersistance.DAOImpl;

namespace SoftVenBusiness
{
    public class ConductoresBO
    {
        private ConductoresDAO conductoresDAO;

        public ConductoresBO()
        {
            conductoresDAO = new ConductoresDAOImpl();
        }

        public int Insertar(string paterno, string materno, string nombres, string numLicencia, int tipoLicenciaId, int puntosAcumulados)
        {
            ConductoresDTO conductor = new ConductoresDTO
            {
                Paterno = paterno,
                Materno = materno,
                Nombres = nombres,
                NumLicencia = numLicencia,
                TipoLicenciaId = tipoLicenciaId,
                PuntosAcumulados = puntosAcumulados
            };
            return conductoresDAO.Insertar(conductor);
        }

        public void Modificar(ConductoresDTO conductor)
        {
            conductoresDAO.Modificar(conductor);
        }

        public void Eliminar(int id)
        {
            ConductoresDTO conductor = new ConductoresDTO();
            conductor.ConductorId = id;
            conductoresDAO.Eliminar(conductor);
        }

        public ConductoresDTO ObtenerPorId(int id)
        {
            return conductoresDAO.ObtenerPorId(id);
        }

        public List<ConductoresDTO> ListarTodos()
        {
            return conductoresDAO.ListarTodos().ToList();
        }
    }
}
