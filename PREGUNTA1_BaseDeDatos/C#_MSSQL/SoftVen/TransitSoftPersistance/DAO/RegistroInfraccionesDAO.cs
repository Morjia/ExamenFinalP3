using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftVenModel;

namespace SoftVenPersistance.DAO
{
    public interface RegistroInfraccionesDAO
    {
        int Insertar(RegistroInfraccionesDTO registro);
        RegistroInfraccionesDTO ObtenerPorId(DateTime fecha, int vehiculoId, int conductorId, int infraccionId);
        BindingList<RegistroInfraccionesDTO> ListarTodos();
        int Modificar(RegistroInfraccionesDTO registro);
        int Eliminar(RegistroInfraccionesDTO registro);
    }
}
